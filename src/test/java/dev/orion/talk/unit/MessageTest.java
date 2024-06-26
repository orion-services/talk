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
package dev.orion.talk.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.orion.talk.model.Message;
import dev.orion.talk.model.User;

import java.time.Instant;

/**
 * This class contains unit tests for the Message class.
 * It tests the functionality of the Message class methods such as setText,
 * setHash, setUser, setTimestamp and the constructor.
 * The tests ensure that the methods set the values correctly and that the
 * getter methods return the expected values.
 */
class MessageTest {

    @Test
    void testSetText() {
        Message message = new Message();
        message.setText("Hello, World!");

        Assertions.assertEquals("Hello, World!", message.getText());
    }

    @Test
    void testSetHash() {
        Message message = new Message();
        message.setHash("abc123");

        Assertions.assertEquals("abc123", message.getHash());
    }

    @Test
    void testSetUser() {
        Message message = new Message();
        User user = new User();
        user.setHash("test-hash");
        message.setUser(user);
        Assertions.assertEquals(user, message.getUser());
    }

    @Test
    void testSetTimestamp() {
        Message message = new Message();
        Instant timestamp = Instant.now();
        message.setTimestamp(timestamp);
        Assertions.assertEquals(timestamp, message.getTimestamp());
    }

    @Test
    void testMessageConstructor() {
        Message message = new Message();

        Assertions.assertNotNull(message.getHash());
    }
}
