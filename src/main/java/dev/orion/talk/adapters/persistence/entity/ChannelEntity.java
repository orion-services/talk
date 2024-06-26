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
package dev.orion.talk.adapters.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Channel entity.
 */
@Entity
@Getter @Setter
@Table(name = "channel")
public class ChannelEntity extends PanacheEntityBase {

    /**
     * Channel id.
     */
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    /** Channel name. */
    private String name;

    /** Channel hash. */
    private String hash;

    /** Massages. */
    @OneToMany(mappedBy = "channel", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<MessageEntity> messages;

    /**
     * The owner of the channel.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    /**
     * Constructor.
     */
    public ChannelEntity() {
        this.messages = new ArrayList<>();
    }

    /**
     * Add message to channel.
     *
     * @param message Message to add.
     */
    public void addMessage(final MessageEntity message) {
        messages.add(message);
    }

}
