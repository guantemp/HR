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


import hr.foxtail.domain.DomainRegistry;
import hr.foxtail.domain.model.organization.account.Account;
import hr.foxtail.domain.model.organization.account.AlipayAccount;
import hr.foxtail.domain.model.organization.account.WeChatAccount;
import hr.foxtail.domain.model.organization.location.Location;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public final class Organization {
    private static final Pattern CREDIT_NUMBER_PATTERN = Pattern.compile("^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-90-9ABCDEFGHJKLMNPQRTUWXY])$");
    private String aboutUs;
    private Account BasicAccount;
    private Account generalAccount;
    private AlipayAccount alipayAccount;
    private WeChatAccount weChatAccount;
    private String telephone;
    private String fax;
    private String email;
    private Location location;
    private String creditNumber;
    private Set<License> licenses;
    private BufferedImage logo;
    private URL web;
    private String mnemonic;
    private Name name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return creditNumber != null ? creditNumber.equals(that.creditNumber) : that.creditNumber == null;
    }

    @Override
    public int hashCode() {
        return creditNumber != null ? creditNumber.hashCode() : 0;
    }

    public String aboutUs() {
        return aboutUs;
    }

    public void addedLicense(License license) {
        if (null == license)
            return;
        if (null == this.licenses)
            licenses = new HashSet<License>();
        if (licenses.add(license))
            DomainRegistry.domainEventPublisher().publish(new OrganizationLicenseAdded(creditNumber, license));
    }

    public void changedAboutUs(String aboutUs) {
        if (this.aboutUs == null && aboutUs == null)
            return;
        if ((this.aboutUs == null && aboutUs != null) || (this.aboutUs != null && !this.aboutUs.equals(aboutUs))) {
            this.aboutUs = aboutUs;
            DomainRegistry.domainEventPublisher().publish(new OrganizationAboutUsChanged(creditNumber, aboutUs));
        }
    }

    public void changedLogo(BufferedImage logo) {
        if (this.logo == null && logo == null)
            return;
        if ((this.logo == null && logo != null) || (this.logo != null && !this.logo.equals(logo))) {
            this.logo = logo;
            DomainRegistry.domainEventPublisher().publish(new OrganizationLogoChanged(creditNumber, logo));
        }
    }

    public String creditNumber() {
        return creditNumber;
    }

    public Iterator<License> licenses() {
        return licenses.iterator();
    }

    public BufferedImage logo() {
        return logo;
    }

    public String mnemonic() {
        return mnemonic;
    }

    public Name name() {
        return name;
    }

    public void removedLicense(License license) {
        if (null != licenses && licenses.remove(license))
            DomainRegistry.domainEventPublisher().publish(new OrganizationLicenseRemoved(creditNumber, license));
    }

    public void renamed(Name name) {
        name = Objects.requireNonNull(name, "name is required");
        if (!this.name.equals(name)) {
            this.name = name;
            DomainRegistry.domainEventPublisher().publish(new OrganizationRenamed(creditNumber, name));
        }
    }

    private void setName(Name name) {
        name = Objects.requireNonNull(name, "name is required");
        this.name = name;
    }

    public OrganizationSnapshot toOrganizationSnapshot() {
        return new OrganizationSnapshot(creditNumber, name.name());
    }
}
