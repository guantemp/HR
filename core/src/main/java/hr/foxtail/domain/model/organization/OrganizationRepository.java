/*
 * @(#}InstitutionRepository.java
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

import java.util.Collection;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.2 2016年11月12日
 */
public interface OrganizationRepository {
    /**
     * @return
     */
    long nextIdentity();

    /**
     * @param creditCode
     * @return
     */
    Organization ofCreditNumber(String creditNumber);

    /**
     * @param id
     * @return
     */
    Organization ofId(long id);

    /**
     * @param mnemonic
     * @return
     */
    Collection<Organization> ofMnemonic(String mnemonic);

    /**
     * @param name
     * @return
     */
    Collection<Organization> ofName(String name);

    /**
     * @param organization
     */
    void remove(Organization organization);

    /**
     * @param organization
     */
    void save(Organization organization);
}
