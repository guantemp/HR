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

package hr.hoprxi.domain.model.employee;

import java.util.StringJoiner;

/**
 * @author <a href="www.hoprxi.com/author/guan xianghuang">guan xiangHuan</a>
 * @version 0.0.1 2020-12-05
 * @since JDK8.0
 */
public class Contacts {
    private String name;
    private String telephone;
    private Relation relation;

    public Contacts(String name, String telephone, Relation relation) {
        this.name = name;
        this.telephone = telephone;
        this.relation = relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacts contacts = (Contacts) o;

        if (name != null ? !name.equals(contacts.name) : contacts.name != null) return false;
        if (telephone != null ? !telephone.equals(contacts.telephone) : contacts.telephone != null) return false;
        return relation == contacts.relation;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Contacts.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("telephone='" + telephone + "'")
                .add("relation=" + relation)
                .toString();
    }

    public enum Relation {
        SPOUSE {
            @Override
            public String toString() {
                return "配偶";
            }
        }, CHILDREN, PARENT, sisters {
            @Override
            public String toString() {
                return "姊妹";
            }
        }, brother {
            @Override
            public String toString() {
                return "兄弟";
            }
        }, grandparent {
            @Override
            public String toString() {
                return "祖父母";
            }
        }, Grandparents {
            @Override
            public String toString() {
                return "外祖父母";
            }
        }
    }
}
