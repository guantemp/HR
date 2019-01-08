/*
 * @(#}Department.java
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


/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK7.0
 * @version 0.0.1 2016年3月28日
 */
public final class Department {
    private String description;
    private String name;
    private long organizationId;

    protected Department(long organizationId, String name, String description) {
        super();
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
    }

    public void changeName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Must provide a name.");
        if (this.name.equals(name))
            throw new IllegalArgumentException("The name is unchanged.");
        this.name = name;
        DomainRegistry.domainEventPublisher().publish(new DepartmentNameChanged(organizationId, name));
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;
        if (organizationId != other.organizationId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (organizationId ^ (organizationId >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public String name() {
        return name;
    }

    public long organizationId() {
        return organizationId;
    }

    @Override
    public String toString() {
        return "Department [organizationId=" + organizationId + ", name=" + name + ", description=" + description + "]";
    }
}
