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

import java.util.UUID;

/**
 * User model.
 */
public class User {

    /** Hash of the user. */
    private String hash;

    /** Name of the user. */
    private String name;

    /**
     * Default constructor.
     */
    public User() {
        this.hash = UUID.randomUUID().toString();
    }

    /**
     * Get user hash.
     *
     * @return User hash.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set user hash.
     *
     * @param hash User hash.
     */
    public void setHash(final String hash) {
        this.hash = hash;
    }

    /**
     * Get user name.
     *
     * @return User name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set user name.
     *
     * @param name User name.
     */
    public void setName(final String name) {
        this.name = name;
    }

}
