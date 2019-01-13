/*
 * @(#}InstitutionOfficeMoved.java
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


import event.foxtail.alpha.domain.model.DomainEvent;
import hr.foxtail.domain.model.location.Location;

import java.time.LocalDateTime;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public class OrganizationLocationTransferred implements DomainEvent {
    private String creditNumber;
    private LocalDateTime occurredOn;
    private Location location;
    private int version;

    public OrganizationLocationTransferred(String creditNumber, Location location) {
        super();
        this.creditNumber = creditNumber;
        this.location = location;
        this.occurredOn = LocalDateTime.now();
        this.version = 1;
    }

    public String creditNumber() {
        return creditNumber;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public Location location() {
        return location;
    }

    @Override
    public int version() {
        return version;
    }
}
