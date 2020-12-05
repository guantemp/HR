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
package hr.hoprxi.domain.model.organization.department;

import hr.hoprxi.domain.DomainRegistry;
import hr.hoprxi.domain.model.location.Location;
import hr.hoprxi.domain.model.organization.Contact;
import hr.hoprxi.domain.model.organization.OrganizationService;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringJoiner;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2020-12-05
 */
public class Department {
    //Depth of department tree
    private static final int DEPTH = 8;
    private String name;
    private String id;
    private String organizationUnifiedSocialCreditCode;
    private Deque<String> treePath;
    private Location location;
    private Contact contact;
    private String description;

    /**
     * @param organizationUnifiedSocialCreditCode
     * @param id
     * @param name
     */
    protected Department(String organizationUnifiedSocialCreditCode, String id, String name) {
        this(organizationUnifiedSocialCreditCode, id, name, null, null, null);
    }

    /**
     * @param organizationUnifiedSocialCreditCode
     * @param id
     * @param name
     * @param location
     * @param contact
     * @param description
     */
    protected Department(String organizationUnifiedSocialCreditCode, String id, String name, Location location, Contact contact, String description) {
        setOrganizationUnifiedSocialCreditCode(organizationUnifiedSocialCreditCode);
        setId(id);
        setName(name);
        setLocation(location);
        this.contact = contact;
        this.description = description;
    }

    private void setLocation(Location location) {
        if (location == null) {
            location = OrganizationService.getOrganizationLocation(organizationUnifiedSocialCreditCode);
        }
        this.location = location;
    }

    private void setOrganizationUnifiedSocialCreditCode(String organizationUnifiedSocialCreditCode) {
        Objects.requireNonNull(organizationUnifiedSocialCreditCode, "organizationUnifiedSocialCreditCode required");
        if (!OrganizationService.validatorOrganizationExists(organizationUnifiedSocialCreditCode))
            throw new IllegalArgumentException("organizationUnifiedSocialCreditCode illegal");
        this.organizationUnifiedSocialCreditCode = organizationUnifiedSocialCreditCode;
    }

    private void setName(String name) {
        this.name = Objects.requireNonNull(id, "name is required.").trim();
    }

    private void setId(String id) {
        this.id = Objects.requireNonNull(id, "id is required.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]")
                .add("description='" + description + "'")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("organizationUnifiedSocialCreditCode='" + organizationUnifiedSocialCreditCode + "'")
                .add("location=" + location)
                .add("contact=" + contact)
                .toString();
    }

    /**
     * support depth is 8
     *
     * @param parent
     */
    public void assignTo(Department parent) {
        if (parent != null) {
            treePath = new ArrayDeque<>();
            if (parent.treePath != null && parent.treePath.size() >= DEPTH)
                throw new IllegalStateException("Allow only depths of" + DEPTH);
            else
                for (String s : parent.treePath) {
                    treePath.offerLast(s);
                }
            treePath.offerLast(parent.id);
            //DomainRegistry.domainEventPublisher().publish(new OrganizationAssignedToOrganization(unifiedSocialCreditCode, parent.unifiedSocialCreditCode));
        }
    }

    /**
     * Will set treePath is <code>NULL</code>
     */
    public void unassign() {
        treePath = null;
        //DomainRegistry.domainEventPublisher().publish(new ResourceUnassigned(id));
    }

    public String name() {
        return name;
    }

    public void rename(String name) {
        name = Objects.requireNonNull(name, "name is required").trim();
        if (!this.name.equals(name)) {
            setName(name);
            DomainRegistry.domainEventPublisher().publish(new DepartmentRenamed(id, name));
        }
    }

    public String id() {
        return id;
    }

    public String organizationId() {
        return organizationUnifiedSocialCreditCode;
    }

    public Contact contact() {
        return contact;
    }

    public String description() {
        return description;
    }

    public Location location() {
        return location;
    }
}
