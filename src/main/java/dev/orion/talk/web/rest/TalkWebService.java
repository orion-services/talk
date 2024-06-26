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
package dev.orion.talk.web.rest;

import dev.orion.talk.adapters.controllers.ServiceController;
import dev.orion.talk.adapters.persistence.entity.MessageEntity;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Talk web service.
 */
@Path("/talk/message")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class TalkWebService {

    /**
     * Controller.
     */
    @Inject
    private ServiceController controller;

    /**
     * Create a message endpoint.
     *
     * @param text the message text
     * @param userName the user name
     * @param userHash the user hash
     * @param channelHash the channel hash
     * @return the message created
     */
    @POST
    @Path("/create")
    public Uni<MessageEntity> create(
            @FormParam("text") @NotEmpty final String text,
            @FormParam("userName") @NotEmpty final String userName,
            @FormParam("userHash") @NotEmpty final String userHash,
            @FormParam("channelHash") @NotEmpty final String channelHash) {
        try {
            return controller.createMessage(
                text, userName, userHash, channelHash)
                .log()
                .onItem().ifNotNull()
                .transform(message -> message)
                .onFailure().transform(e -> {
                    String message = e.getMessage();
                    throw new ServiceException(message,
                            Response.Status.BAD_REQUEST);
                });
        } catch (Exception e) {
            String message = e.getMessage();
            throw (ServiceException) new ServiceException(message,
                    Response.Status.BAD_REQUEST);
        }
    }

}
