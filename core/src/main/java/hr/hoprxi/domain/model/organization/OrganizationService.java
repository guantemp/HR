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

import hr.hoprxi.domain.model.location.Location;

import java.util.Objects;

/**
 * @author <a href="www.hoprxi.com/author/guan xianghuang">guan xiangHuan</a>
 * @version 0.0.1 2020-12-05
 * @since JDK8.0
 */
public class OrganizationService {
    private OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository required");
    }

    public static boolean validatorOrganizationExists(String unifiedSocialCreditCode) {
        return true;
    }

    public static Location getOrganizationLocation(String unifiedSocialCreditCode) {
        return null;
    }
}
