/*
 * Copyright (c) 2020 www.hoprxi.com All rights Reserved.
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

import java.time.LocalDateTime;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-13
 */
public class OrganizationAssignedToOrganization implements DomainEvent {
    private String unifiedSocialCreditCode;
    private String parentUnifiedSocialCreditCode;

    public OrganizationAssignedToOrganization(String unifiedSocialCreditCode, String parentUnifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
        this.parentUnifiedSocialCreditCode = parentUnifiedSocialCreditCode;
    }

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }

    @Override
    public int version() {
        return 0;
    }
}
