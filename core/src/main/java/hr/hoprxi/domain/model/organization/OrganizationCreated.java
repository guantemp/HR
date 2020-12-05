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
import hr.hoprxi.domain.model.brace.Name;
import hr.hoprxi.domain.model.location.Location;
import hr.hoprxi.domain.model.organization.account.Account;
import hr.hoprxi.domain.model.organization.account.Alibaba;
import hr.hoprxi.domain.model.organization.account.WeChat;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public class OrganizationCreated implements DomainEvent {
    private LocalDateTime occurredOn;
    private int version;
    private String aboutUs;
    private Account BasicAccount;
    private Account generalAccount;
    private Alibaba alibaba;
    private WeChat weChat;
    private Contact contact;
    private Location location;
    private String unifiedSocialCreditCode;
    private Set<License> licenses;
    private BufferedImage logo;
    private URL homepage;
    private Name name;
    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public int version() {
        return version;
    }
}

