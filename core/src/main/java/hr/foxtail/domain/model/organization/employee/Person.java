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
package hr.foxtail.domain.model.organization.employee;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月9日
 */
public final class Person {
    private String email;
    private long id;
    private String mobile;
    private String name;

    /**
     * @param id
     * @param name
     * @param email
     * @param mobile
     */
    public Person(long id, String name, String email, String mobile) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    public long id() {
        return id;
    }

    public String mobile() {
        return mobile;
    }

    public String name() {
        return name;
    }
}
