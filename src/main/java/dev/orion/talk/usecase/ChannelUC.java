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
package dev.orion.talk.usecase;

import dev.orion.talk.model.Channel;

/**
 * Channel use case.
 */
public class ChannelUC {

    /**
     * Create a channel.
     *
     * @param name  Channel name.
     * @param hash  Channel hash.
     * @return Channel object.
     */
    public Channel createChannel(final String name, final String hash) {
        Channel channel = null;
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name can't be empty");
        } else {
            channel = new Channel();
            if (hash != null) {
               channel.setHash(hash);
            }
            channel.setName(name);
        }
        return channel;
    }

}
