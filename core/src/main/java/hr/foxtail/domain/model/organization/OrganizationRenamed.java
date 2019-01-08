/*
 * @(#}InstitutionNameChanged.java
 *
 * Copyright 2016 www.foxtail.cc rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hr.foxtail.domain.model.organization;

import cc.foxtail.domainevent.domain.model.DomainEvent;

import java.time.LocalDateTime;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.2 2016年12月31日
 */
public final class OrganizationRenamed implements DomainEvent {
    private long id;
    private LocalDateTime occurredOn;
    private int version;
    private String name;

    public OrganizationRenamed(long id, String name) {
        super();
        this.id = id;
        this.name = name;
        this.occurredOn = LocalDateTime.now();
        this.version = 1;
    }

    public long id() {
        return id;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public int version() {
        return version;
    }

    public String name() {
        return name;
    }
}

