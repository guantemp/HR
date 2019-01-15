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
import hr.foxtail.domain.model.brace.Name;
import hr.foxtail.domain.model.location.Location;
import hr.foxtail.domain.model.organization.account.Account;
import hr.foxtail.domain.model.organization.account.Alibaba;
import hr.foxtail.domain.model.organization.account.WeChat;
import hr.foxtail.domain.model.organization.department.Department;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public class Organization {
    private static final Pattern CREDIT_NUMBER_PATTERN = Pattern.compile("^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-90-9ABCDEFGHJKLMNPQRTUWXY])$");
    //Depth of organization tree
    private static final int DEPTH = 16;
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
    private Deque<String> treePath;

    /**
     * @param unifiedSocialCreditCode
     * @param name
     */
    public Organization(String unifiedSocialCreditCode, Name name) {
        this(unifiedSocialCreditCode, name, null, null, null, null, null, null, null, null, null, null);
    }

    public Organization(String unifiedSocialCreditCode, Name name, Location location, Contact contact, String aboutUs, BufferedImage logo,
                        Account basicAccount, Account generalAccount, Alibaba alibaba, WeChat weChat, Set<License> licenses, URL homepage) {
        setUnifiedSocialCreditCode(unifiedSocialCreditCode);
        setName(name);
        this.location = location;
        this.contact = contact;
        this.aboutUs = aboutUs;
        this.logo = logo;
        this.BasicAccount = basicAccount;
        this.generalAccount = generalAccount;
        this.alibaba = alibaba;
        this.weChat = weChat;
        this.licenses = licenses;
        this.homepage = homepage;
    }

    private void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        Matcher matcher = CREDIT_NUMBER_PATTERN.matcher(unifiedSocialCreditCode);
        if (!matcher.matches())
            throw new IllegalArgumentException("Illegal Unified Social Credit Code");
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return unifiedSocialCreditCode != null ? unifiedSocialCreditCode.equals(that.unifiedSocialCreditCode) : that.unifiedSocialCreditCode == null;
    }

    public Department establishDepartment(String name, String desc) {
        return null;
        //return new Department(unifiedSocialCreditCode,name,desc);
    }

    @Override
    public int hashCode() {
        return unifiedSocialCreditCode != null ? unifiedSocialCreditCode.hashCode() : 0;
    }

    public String aboutUs() {
        return aboutUs;
    }

    public OrganizationSnapshot toOrganizationSnapshot() {
        return new OrganizationSnapshot(unifiedSocialCreditCode, name);
    }

    public void addLicense(License license) {
        if (null == license)
            return;
        if (null == this.licenses)
            licenses = new HashSet<License>();
        if (licenses.add(license))
            DomainRegistry.domainEventPublisher().publish(new OrganizationLicenseAdded(unifiedSocialCreditCode, license));
    }

    public void changeAboutUs(String aboutUs) {
        if (this.aboutUs == null && aboutUs == null)
            return;
        if ((this.aboutUs == null && aboutUs != null) || (this.aboutUs != null && !this.aboutUs.equals(aboutUs))) {
            this.aboutUs = aboutUs;
            DomainRegistry.domainEventPublisher().publish(new OrganizationAboutUsChanged(unifiedSocialCreditCode, aboutUs));
        }
    }

    public void changeLogo(BufferedImage logo) {
        if (this.logo == null && logo == null)
            return;
        if ((this.logo == null && logo != null) || (this.logo != null && !this.logo.equals(logo))) {
            this.logo = logo;
            DomainRegistry.domainEventPublisher().publish(new OrganizationLogoChanged(unifiedSocialCreditCode, logo));
        }
    }

    public String creditNumber() {
        return unifiedSocialCreditCode;
    }

    public Iterator<License> licenses() {
        return licenses.iterator();
    }

    public BufferedImage logo() {
        return logo;
    }

    public Name name() {
        return name;
    }

    public void removeLicense(License license) {
        if (null != licenses && licenses.remove(license))
            DomainRegistry.domainEventPublisher().publish(new OrganizationLicenseRemoved(unifiedSocialCreditCode, license));
    }

    public void rename(Name name) {
        Objects.requireNonNull(name, "name is required");
        if (!this.name.equals(name)) {
            this.name = name;
            DomainRegistry.domainEventPublisher().publish(new OrganizationRenamed(unifiedSocialCreditCode, name));
        }
    }

    private void setName(Name name) {
        this.name = Objects.requireNonNull(name, "name is required");
    }

    /**
     * support depth is 16
     *
     * @param parent
     */
    public void assignTo(Organization parent) {
        if (parent != null) {
            treePath = new ArrayDeque<>();
            if (parent.treePath != null && parent.treePath.size() >= DEPTH)
                throw new IllegalStateException("Allow only depths of" + DEPTH);
            else
                for (String s : parent.treePath) {
                    treePath.offerLast(s);
            }
            treePath.offerLast(parent.unifiedSocialCreditCode);
            DomainRegistry.domainEventPublisher().publish(new OrganizationAssignedToOrganization(unifiedSocialCreditCode, parent.unifiedSocialCreditCode));
        }
    }

    /**
     * Will set treePath is <code>NULL</code>
     */
    public void unassign() {
        treePath = null;
        //DomainRegistry.domainEventPublisher().publish(new ResourceUnassigned(id));
    }


    public Account basicAccount() {
        return BasicAccount;
    }

    public Account generalAccount() {
        return generalAccount;
    }

    public Alibaba alibaba() {
        return alibaba;
    }

    public WeChat weChat() {
        return weChat;
    }

    public Contact contact() {
        return contact;
    }

    public Location location() {
        return location;
    }

    public Iterator<License> getLicenses() {
        return licenses.iterator();
    }

    public URL homepage() {
        return homepage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
                .add("aboutUs='" + aboutUs + "'")
                .add("BasicAccount=" + BasicAccount)
                .add("generalAccount=" + generalAccount)
                .add("alibaba=" + alibaba)
                .add("weChat=" + weChat)
                .add("contact=" + contact)
                .add("location=" + location)
                .add("unifiedSocialCreditCode='" + unifiedSocialCreditCode + "'")
                .add("licenses=" + licenses)
                .add("logo=" + logo)
                .add("homepage=" + homepage)
                .add("name=" + name)
                .toString();
    }
}
