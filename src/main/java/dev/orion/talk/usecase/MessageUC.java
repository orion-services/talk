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

import java.time.Instant;

import dev.orion.talk.model.Message;

/**
 * Message use case.
 */
public class MessageUC {

    /**
     * Create a message.
     *
     * @param text  Message text.
     * @return Message object.
     */
    public Message createMessage(final String text) {
        Message message = new Message();
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("The text can't be empty");
        } else {
            message.setText(text);
            message.setTimestamp(Instant.now());
        }
        return message;
    }

}
