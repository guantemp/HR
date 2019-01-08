/*
 * Copyright 2019 www.foxtail.cc rights Reserved.
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
import organization.foxtail.domain.model.organization.account.Account;

import java.time.LocalDateTime;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年12月20日
 */
public final class OrganizationAccountAdded implements DomainEvent {
    private Account account;
    private long id;
    private LocalDateTime occurredOn;
    private int version;

    /**
     * @param id
     * @param account
     */
    public OrganizationAccountAdded(long id, Account account) {
        super();
        this.id = id;
        this.account = account;
        this.occurredOn = LocalDateTime.now();
        this.version = 1;
    }

    public Account account() {
        return account;
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
}
