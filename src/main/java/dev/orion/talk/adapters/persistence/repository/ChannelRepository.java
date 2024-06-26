/**
 * @License
 * Copyright 2024 Orion Services @ https://github.com/orion-services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.orion.talk.adapters.persistence.repository;

import dev.orion.talk.adapters.persistence.entity.ChannelEntity;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * A {@link PanacheRepository} for {@link ChannelEntity}.
 */
@ApplicationScoped
public class ChannelRepository implements PanacheRepository<ChannelEntity> {

    /**
     * Persists a channel entity.
     *
     * @param channel A {@link ChannelEntity}
     * @return A {@link Uni} of {@link ChannelEntity}
     */
    public Uni<ChannelEntity> save(final ChannelEntity channel) {
        return findByName(channel.getName())
            .onItem().ifNotNull().transform(entity -> entity)
            .onItem().ifNull().switchTo(
                findByHash(channel.getHash())
                    .onItem().ifNotNull().transform(entity -> entity)
                    .onItem().ifNull().switchTo(
                        Panache.withTransaction(() -> persist(channel))
                            .onItem().transform(entity -> entity)
                    )
            );
    }

    /**
     * Find a channel by hash.
     *
     * @param hash
     * @return A {@link Uni} of {@link ChannelEntity}
     */
    private Uni<ChannelEntity> findByHash(final String hash) {
        return find("hash", hash).firstResult();
    }

    /**
     * Find a channel by name.
     *
     * @param name
     * @return A {@link Uni} of {@link ChannelEntity}
     */
    private Uni<ChannelEntity> findByName(final String name) {
        return find("name", name).firstResult();
    }

}
