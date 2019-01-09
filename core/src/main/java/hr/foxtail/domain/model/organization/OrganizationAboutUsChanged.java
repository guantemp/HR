/*
 * @(#}InstitutionDescriptionChanged.java
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

import java.time.LocalDateTime;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public final class OrganizationAboutUsChanged implements DomainEvent {
    private String aboutUs;
    private String creditNumber;
    private LocalDateTime occurredOn;
    private int version;

    public OrganizationAboutUsChanged(String creditNumber, String aboutUs) {
        super();
        this.creditNumber = creditNumber;
        this.aboutUs = aboutUs;
        this.occurredOn = LocalDateTime.now();
        this.version = 1;
    }

    public String aboutUs() {
        return aboutUs;
    }

    public String creditNumber() {
        return creditNumber;
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
