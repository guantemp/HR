/*
 * Copyright (c) 2019 www.foxtail.cc All rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hr.hoprxi.domain.model.organization.department;


import event.foxtail.alpha.domain.model.DomainEvent;
import hr.hoprxi.domain.model.location.Location;
import hr.hoprxi.domain.model.organization.Contact;

import java.time.LocalDateTime;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public  class DepartmentCreated implements DomainEvent {
    private String description;
    private String name;
    private String id;
    private String organizationId;
    private Contact contact;
    private Location location;
    private LocalDateTime occurredOn;
    private int version;

    public DepartmentCreated(String description, String name, String id, String organizationId, Contact contact, Location location) {
        this.description = description;
        this.name = name;
        this.id = id;
        this.organizationId = organizationId;
        this.contact = contact;
        this.location = location;
        version = 1;
        occurredOn = LocalDateTime.now();
    }

    public String description() {
        return description;
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }

    public String organizationId() {
        return organizationId;
    }

    public Contact contact() {
        return contact;
    }

    public Location location() {
        return location;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public int version() {
        return version;
    }
}
