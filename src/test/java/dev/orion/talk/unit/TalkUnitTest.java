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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.orion.talk.model.Message;
import dev.orion.talk.usecase.MessageUC;
import io.vertx.core.cli.annotations.Description;

public class TalkUnitTest {

    MessageUC uc = new MessageUC();

    @Test
    @Description("Unit: Create a message")
    void createMessage() {
        String text = "Hello world";
        Message message = uc.createMessage(text);
        assertEquals(message.getText(), text);
    }

    @Test
    @Description("Unit: Create an empty message")
    void createEmptyMessage() {
        String text = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            uc.createMessage(text);
        });
    }

}
