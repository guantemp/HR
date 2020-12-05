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

package hr.hoprxi.infrastructure.persistence;

import com.arangodb.ArangoDatabase;
import com.arangodb.model.VertexUpdateOptions;
import hr.hoprxi.domain.model.organization.Organization;
import hr.hoprxi.domain.model.organization.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="www.hoprxi.com/author/guan xianghuang">guan xiangHuan</a>
 * @version 0.0.1 2020-12-05
 * @since JDK8.0
 */
public class ArangoDBOrganizationRepository implements OrganizationRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArangoDBOrganizationRepository.class);
    private static final VertexUpdateOptions UPDATE_OPTIONS = new VertexUpdateOptions().keepNull(false);
    private ArangoDatabase identity = ArangoDBUtil.getResource().db("hr");

    public ArangoDBOrganizationRepository() {
    }

    @Override
    public Organization find(String unifiedSocialCreditCode) {
        return null;
    }

    @Override
    public Organization[] child(String parentUnifiedSocialCreditCode) {
        return new Organization[0];
    }

    @Override
    public Organization[] findByMnemonic(String mnemonic) {
        return new Organization[0];
    }

    @Override
    public Organization[] findByName(String name) {
        return new Organization[0];
    }

    @Override
    public void remove(String unifiedSocialCreditCode) {

    }

    @Override
    public void save(Organization organization) {

    }
}
