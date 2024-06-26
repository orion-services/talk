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
package dev.orion.talk.web.grapthql;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import dev.orion.talk.adapters.controllers.ServiceController;
import dev.orion.talk.adapters.persistence.entity.ChannelEntity;
import dev.orion.talk.adapters.persistence.entity.MessageEntity;
import dev.orion.talk.adapters.persistence.entity.UserEntity;
import dev.orion.talk.web.rest.ServiceException;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * GraphQl.
 */
@GraphQLApi
@RequestScoped
@RolesAllowed("user")
public class GraphQl {

    /**
     * Service controller.
     */
    @Inject
    private ServiceController controller;

    /**
     * User email.
     */
    @Inject
    @Claim(standard = Claims.email)
    private String email;

    /**
     * Creates a new channel with the given channel entity and user entity.
     *
     * @param channel the channel entity to create
     * @param user the user entity creating the channel
     * @return a Uni that emits the created channel entity, or null if the
     * creation failed
     */
    @Mutation
    public Uni<ChannelEntity> createChannel(
        final ChannelEntity channel,
        final UserEntity user) {
        return controller.addChannel(channel, user)
            .onItem().ifNotNull().transform(m -> m);
    }

    /**
     * Create a Message thought Graphql.
     *
     * @param text        Message text.
     * @param userName    User name.
     * @param userHash    User hash.
     * @param channelHash Channel hash.
     * @return Message object.
     */
    @Mutation
    public Uni<MessageEntity> createMessage(
            final String text,
            final String channelHash,
            final String userHash,
            final String userName) {

        return controller.createMessage(text, userName, userHash, channelHash)
                .onItem().ifNotNull().transform(m -> m)
                .onItem().ifNull().failWith(() -> {
                    throw new ServiceException("Message not created");
                });
    }

    /**
     * Get all Messages of a channel.
     *
     * @param channelHash Channel hash.
     * @return List of Messages.
     */
    @Query("getMessages")
    @Description("Get all Messages of a channel")
    public Uni<List<MessageEntity>> getMessages(final String channelHash) {
        return controller.getMessages(channelHash);
    }

}
