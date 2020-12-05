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
package hr.hoprxi.domain.model.organization;


import event.hoprxi.domain.model.DomainEvent;
import hr.hoprxi.domain.model.organization.account.Account;

import java.time.LocalDateTime;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public  class OrganizationGeneralAccountChanged implements DomainEvent {
    private Account generalAccount;
    private String creditNumber;
    private LocalDateTime occurredOn;
    private int version;

    /**
     * @param creditNumber
     * @param generalAccount
     */
    public OrganizationGeneralAccountChanged(String creditNumber, Account generalAccount) {
        super();
        this.creditNumber = creditNumber;
        this.generalAccount = generalAccount;
        this.occurredOn = LocalDateTime.now();
        this.version = 1;
    }

    public Account generalAccount() {
        return generalAccount;
    }

    public String id() {
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
