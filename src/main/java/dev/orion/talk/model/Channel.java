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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Channel model.
 */
public class Channel {

    /** Channel name. */
    private String name;

    /** Channel hash. */
    private String hash;

    /** Messages. */
    private List<Message> messages;

    /** User. */
    private User user;

    /**
     * Constructor.
     */
    public Channel() {
        this.hash = UUID.randomUUID().toString();
        this.messages = new ArrayList<>();
    }

    /**
     * Get channel hash.
     *
     * @return Channel hash.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set channel hash.
     *
     * @param hash Channel hash.
     */
    public void setHash(final String hash) {
        this.hash = hash;
    }

    /**
     * Get channel name.
     *
     * @return Channel name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set channel name.
     *
     * @param name Channel name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get messages.
     *
     * @return A list of messages.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Set messages.
     *
     * @param messages A list of messages.
     */
    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Add message.
     *
     * @param message A Message object.
     */
    public void addMessage(final Message message) {
        this.messages.add(message);
    }

    /**
     * Gets the user associated with this channel.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this channel.
     *
     * @param user the user to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

}
