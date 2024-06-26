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
package dev.orion.talk.adapters.controllers;

import java.util.List;
import java.util.UUID;

import dev.orion.talk.adapters.persistence.entity.ChannelEntity;
import dev.orion.talk.adapters.persistence.entity.MessageEntity;
import dev.orion.talk.adapters.persistence.entity.UserEntity;
import dev.orion.talk.model.Channel;
import dev.orion.talk.model.Message;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Service Controller.
 */
@ApplicationScoped
@WithSession
public class ServiceController extends Controller {

    /** Basic query for hash. */
    private static final String HASH_QUERY = "hash = ?1";

    /**
     * Creates a channel.
     *
     * @param channel A {@link ChannelEntity} with the channel
     * @param user    A {@link UserEntity} with the user
     * @return  A {@link Uni} of {@link Channel}
     */
    public Uni<ChannelEntity> addChannel(
            final ChannelEntity channel,
            final UserEntity user) {

        return this.findUser(user.getHash(), user.getName())
            .onItem().ifNotNull().transformToUni(u -> {
                Channel model = channelUC.createChannel(
                    channel.getName(),
                    channel.getHash());
                ChannelEntity ce = mapper.map(model, ChannelEntity.class);
                ce.setUser(u);
                return channelRepo.save(ce)
                    .log()
                    .onItem().ifNotNull().transform(c -> c);
            });
    }

    /**
     * Creates a message.
     *
     * @param text        A {@link String} with the message text
     * @param userName    A {@link String} with the user name
     * @param userHash    A {@link String} with the user hash
     * @param channelHash A {@link String} with the channel hash
     * @return A {@link Uni} of {@link MessageEntity}
     */
    public Uni<MessageEntity> createMessage(final String text,
        final String userName, final String userHash,
        final String channelHash) {
        return findUser(userHash, userName)
                .onItem().transformToUni(user -> {
                    return findChannel(channelHash)
                            .onItem().ifNotNull().transformToUni(channel -> {
                                // Create message
                                Message message = messageUC.createMessage(text);
                                // Map message to message entity
                                MessageEntity messageEntity = mapper.map(
                                        message, MessageEntity.class);

                                // Verify if channel has a name
                                channel.setName(channel.getName() == null
                                        ? UUID.randomUUID().toString()
                                        : channel.getName());

                                // Set message channel and user
                                messageEntity.setChannel(channel);
                                user.setName(userName);
                                messageEntity.setUser(user);
                                channel.addMessage(messageEntity);

                                // Persist message
                                return messageRepo.save(messageEntity)
                                    .onItem().ifNotNull().transform(m -> m);
                            });
                });
    }

    /**
     * Gets all messages.
     *
     * @param channelHash A {@link String} hash of the channel
     * @return A {@link Uni} of {@link List} of {@link MessageEntity}
     */
    public Uni<List<MessageEntity>> getMessages(final String channelHash) {
        return channelRepo.find(HASH_QUERY, channelHash).firstResult()
            .onItem().ifNotNull().transform(ChannelEntity::getMessages);
    }

    /**
     * Finds a user by hash.
     *
     * @param userHash A {@link String} hash of the user
     * @param name     The {@link String} name of the user
     * @return A {@link Uni} of {@link UserEntity}
     */
    private Uni<UserEntity> findUser(final String userHash, final String name) {
        return userRepo.find(HASH_QUERY, userHash).firstResult()
            .onItem().ifNotNull().transform(user -> user)
            .onItem().ifNull().continueWith(() -> {
                UserEntity user = new UserEntity();
                user.setHash(userHash);
                user.setName(name);
                return user;
            });
    }

    /**
     * Finds a channel by hash.
     *
     * @param channelHash A {@link String} hash of the user
     * @return A {@link Uni} of {@link ChannelEntity}
     */
    private Uni<ChannelEntity> findChannel(final String channelHash) {
        return channelRepo.find(HASH_QUERY, channelHash).firstResult()
            .onItem().ifNotNull().transform(user -> user)
            .onItem().ifNull().continueWith(() -> {
                ChannelEntity channel = new ChannelEntity();
                channel.setHash(channelHash);
                return channel;
            });
    }


}
