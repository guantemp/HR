/*
 * OrganizationService.java
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

import cc.foxtail.assistant.util.PinYinUtil;
import cc.foxtail.investor.domain.model.organization.Organization.Status;
import cc.foxtail.investor.domain.model.share.InvestmentPie;
import cc.foxtail.investor.domain.model.share.SharePie;

import java.awt.image.BufferedImage;
import java.util.Set;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月29日
 */
public class OrganizationFactory {

    /**
     * @param id
     * @param name
     * @return
     */
    public static Organization prepare(String id, String name) {
        return new Organization(id, null, name, PinYinUtil.toShortPinYing(name, true), null, null, Status.PREPARE, null,
                null, null, null, null);
    }

    /**
     * @param id
     * @param name
     * @param investmentPie
     * @param sharePie
     * @param address
     * @return
     */
    public static Organization prepare(String id, String name, InvestmentPie investmentPie, SharePie sharePie,
                                       Address address) {
        return new Organization(id, null, name, PinYinUtil.toShortPinYing(name, true), investmentPie, sharePie,
                Status.PREPARE, address, null, null, null, null);
    }

    /**
     * @param id
     * @param name
     * @param investmentPie
     * @param sharePie
     * @param address
     * @param licenses
     * @param logo
     * @param aboutUs
     * @return
     */
    public static Organization prepare(String id, String name, InvestmentPie investmentPie, SharePie sharePie,
                                       Address address, Set<License> licenses, BufferedImage logo, String aboutUs) {
        return new Organization(id, "", name, PinYinUtil.toShortPinYing(name, true), investmentPie, sharePie,
                Status.PREPARE, address, licenses, null, logo, aboutUs);
    }
}
