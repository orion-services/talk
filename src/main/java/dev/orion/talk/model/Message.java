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
package dev.orion.talk.model;

import java.time.Instant;
import java.util.UUID;

/**
 * Message model.
 */
public class Message {

    /** Message text. */
    private String text;

    /** Message hash. */
    private String hash;

    /** The owner of the message. */
    private User user;

    /** The owner of the message. */
    private Channel channel;

    /** The timestamp of the message. */
    private Instant timestamp;

    /**
     * Message constructor.
     */
    public Message() {
        this.hash = UUID.randomUUID().toString();
    }

    /**
     * Get message text.
     *
     * @return Message text.
     */
    public String getText() {
        return text;
    }

    /**
     * Set message text.
     *
     * @param text Message text.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Get message hash.
     *
     * @return Message hash.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set message hash.
     *
     * @param hash Message hash.
     */
    public void setHash(final String hash) {
        this.hash = hash;
    }

    /**
     * Get message owner.
     *
     * @return Message owner.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set message owner.
     *
     * @param user Message owner.
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Get message channel.
     * @return Message channel.
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Set message channel.
     *
     * @param channel Message channel.
     */
    public void setChannel(final Channel channel) {
        this.channel = channel;
    }

    /**
     * Get message timestamp.
     *
     * @return Message timestamp.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Set message timestamp.
     *
     * @param timestamp Message timestamp.
     */
    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }

}
