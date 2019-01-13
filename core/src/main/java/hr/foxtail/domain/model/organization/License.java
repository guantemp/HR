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

import java.awt.image.BufferedImage;
import java.time.LocalDate;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public final class License {
    private LocalDate expiryOn;
    private LocalDate issueOn;
    private String issuer;
    private String name;
    private String number;
    private BufferedImage picture;

    /**
     * @param name
     * @param number
     */
    public License(String name, String number) {
        this(name, number, null, null, null, null);
    }

    public License(String name, String number, String issuer, LocalDate issuerOn, LocalDate expiryOn, BufferedImage picture) {
        setName(name);
        setNumber(number);
        this.issuer = issuer;
        this.issueOn = issuerOn;
        this.expiryOn = expiryOn;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        License other = (License) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }

    public LocalDate expiryOn() {
        return expiryOn;
    }

    public boolean isExpired() {
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    public LocalDate issueOn() {
        return issueOn;
    }

    public String issuer() {
        return issuer;
    }

    public String name() {
        return name;
    }

    public String number() {
        return number;
    }

    public BufferedImage picture() {
        return picture;
    }

    protected void setName(String name) {
        if (null == name || name.trim().isEmpty())
            throw new IllegalArgumentException("Must provide a name or initial.");
        this.name = name;
    }

    protected void setNumber(String number) {
        if (null == number || number.trim().isEmpty())
            throw new IllegalArgumentException("Must provide a number or initial.");
        this.number = number;
    }

    @Override
    public String toString() {
        return "License [name=" + name + ", number=" + number + ", issuer=" + issuer + ", issueOn=" + issueOn
                + ", expiryOn=" + expiryOn + ", picture=" + picture + "]";
    }

}
