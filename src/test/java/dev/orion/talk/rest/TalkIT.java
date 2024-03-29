/**
 * @License
 * Copyright 2023 Orion Services @ https://github.com/orion-services
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
package dev.orion.talk.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.cli.annotations.Description;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class TalkIT {

    @Test
    @Order(1)
    @Description("Create a message")
    void createMessage() {
        given().when()
            .param("text", "hello world")
            .param("userHash", "123456789")
            .param("channelHash", "123456789")
            .post("/talk/message/create")
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("text", is("hello world"));
    }

    @Test
    @Order(2)
    @Description("Create an empty message")
    void createEmptyMessage() {
        given().when()
            .param("text", "")
            .post("/talk/message/create")
        .then()
             .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }

}
