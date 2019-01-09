/*
 *  Copyright 2018 www.foxtail.cc rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hr.foxtail.domain.model.organization;


import mi.foxtail.to.PinYin;

import java.util.Objects;
import java.util.StringJoiner;

/***
 * @author <a href="www.foxtail.cc/authors/guan xianghuang">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2018-06-11
 */
public final class Name {
    private String name;
    private String mnemonic;

    /**
     * @param name
     * @param mnemonic
     * @throws IllegalArgumentException if name is <code>NULL</code>
     */
    public Name(String name, String mnemonic) {
        setName(name);
        this.mnemonic = mnemonic;
    }


    /**
     * @param name
     * @throws IllegalArgumentException if name is <code>NULL</code>
     */
    public Name(String name) {
        setName(name);
        this.mnemonic = PinYin.toShortPinYing(this.name);
    }

    public String name() {
        return name;
    }

    private void setName(String name) {
        this.name = Objects.requireNonNull(name, "name required").trim();
    }

    public String mnemonic() {
        return mnemonic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name1 = (Name) o;

        if (name != null ? !name.equals(name1.name) : name1.name != null) return false;
        return mnemonic != null ? mnemonic.equals(name1.mnemonic) : name1.mnemonic == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mnemonic != null ? mnemonic.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Name.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("mnemonic='" + mnemonic + "'")
                .toString();
    }
}
