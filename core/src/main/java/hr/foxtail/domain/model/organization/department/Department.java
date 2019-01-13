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
package hr.foxtail.domain.model.organization.department;


import hr.foxtail.domain.model.location.Location;
import hr.foxtail.domain.model.organization.Contact;

import java.util.Deque;
import java.util.Objects;
import java.util.StringJoiner;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public final class Department {
    private String description;
    //Depth of department tree
    private static final int DEPTH = 8;
    private String name;
    private String id;
    private String organizationId;
    private Deque<String> treePath;
    private Location location;
    private Contact contact;

    protected Department(String organizationId, String id, String name) {
        this(organizationId, id, name, null, null, null);
    }

    protected Department(String organizationId, String id, String name, Location location, Contact contact, String description) {
        setOrganizationId(organizationId);
        setId(id);
        setName(name);
        this.location = location;
        this.contact = contact;
        this.description = description;
    }

    private void setOrganizationId(String organizationId) {
    }

    private void setName(String name) {
        this.name = Objects.requireNonNull(id, "name is required.");
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
                .add("organizationId='" + organizationId + "'")
                .add("location=" + location)
                .add("contact=" + contact)
                .toString();
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }

    public String organizationId() {
        return organizationId;
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
