/*
 * @(#}DepartmentCreated.java
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
 * @since JDK7.0
 * @version 0.0.1 2016年3月29日
 */
public final class DepartmentCreated implements DomainEvent {
    private String description;
    private long institutionId;
    private String name;
    private LocalDateTime occurredOn;
    private int version;

    protected DepartmentCreated(long institutionId, String name, String description) {
        super();
        this.occurredOn = LocalDateTime.now();
        this.version = 1;
        this.name = name;
        this.description = description;
        this.institutionId = institutionId;
    }

    public String description() {
        return description;
    }

    public long institutionId() {
        return institutionId;
    }

    public String name() {
        return name;
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