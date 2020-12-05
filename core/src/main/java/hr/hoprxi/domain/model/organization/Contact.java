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
package hr.hoprxi.domain.model.organization;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * @author <a href="www.hoprxi.com/authors/guan xianghuang">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2018-07-13
 */
public  class Contact {
    private static final Pattern TELEPHONE = Pattern.compile("^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    private String telephone;
    private String email;
    private String fax;

    public Contact(String telephone, String fax, String email) {
        setPhone(telephone, fax);
        setEmail(email);
    }

    private void setEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches())
            throw new IllegalArgumentException("error email format!");
        this.email = email;
    }

    private void setPhone(String telephone, String fax) {
        if (fax == null && telephone == null)
            throw new IllegalArgumentException("telephone or fax needs at least one");
        if (fax != null)
            setFax(fax);
        if (telephone != null)
            setTelephone(telephone);
    }

    private void setFax(String fax) {
        Matcher matcher = TELEPHONE.matcher(fax);
        if (!matcher.matches())
            throw new IllegalArgumentException("Not a valid fax number!");
        this.fax = fax;
    }

    private void setTelephone(String telephone) {
        Matcher matcher = TELEPHONE.matcher(telephone);
        if (!matcher.matches())
            throw new IllegalArgumentException("Not a valid telephone number!");
        this.telephone = telephone;
    }

    public String telephone() {
        return telephone;
    }

    public String fax() {
        return fax;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (telephone != null ? !telephone.equals(contact.telephone) : contact.telephone != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return fax != null ? fax.equals(contact.fax) : contact.fax == null;
    }

    @Override
    public int hashCode() {
        int result = telephone != null ? telephone.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
                .add("telephone='" + telephone + "'")
                .add("email='" + email + "'")
                .add("fax='" + fax + "'")
                .toString();
    }
}
