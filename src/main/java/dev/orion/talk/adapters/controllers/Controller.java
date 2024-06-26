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

import org.modelmapper.ModelMapper;

import dev.orion.talk.adapters.persistence.repository.ChannelRepository;
import dev.orion.talk.adapters.persistence.repository.MessageRepository;
import dev.orion.talk.adapters.persistence.repository.UserRepository;
import dev.orion.talk.usecase.ChannelUC;
import dev.orion.talk.usecase.MessageUC;
import jakarta.inject.Inject;

/**
 * Controller.
 */
public class Controller {

    /**
     * Message repository.
     */
    @Inject
    protected MessageRepository messageRepo;

    /**
     * User repository.
     */
    @Inject
    protected UserRepository userRepo;

    /**
     * Channel repository.
     */
    @Inject
    protected ChannelRepository channelRepo;

    /**
     * Channel use case.
     */
    protected ChannelUC channelUC = new ChannelUC();

    /**
     * Message use case.
     */
    protected MessageUC messageUC = new MessageUC();

    /**
     * Model mapper.
     */
    protected ModelMapper mapper = new ModelMapper();

}
