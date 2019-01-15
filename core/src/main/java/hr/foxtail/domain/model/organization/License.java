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
import java.util.Objects;
import java.util.StringJoiner;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public class License {
    private BufferedImage positive;
    private LocalDate expiryOn;
    private LocalDate issueOn;
    private String issuer;
    private BufferedImage opposite;
    private String number;
    private String name;

    public License(String name, String number) {
        this(name, number, null, null, null, null, null);
    }

    public License(String name, String number, String issuer, LocalDate issuerOn, LocalDate expiryOn, BufferedImage positive, BufferedImage opposite) {
        setName(name);
        setNumber(number);
        this.issuer = issuer;
        this.issueOn = issuerOn;
        this.expiryOn = expiryOn;
        this.positive = positive;
        this.opposite = opposite;
    }

    private void setName(String name) {
        name = Objects.requireNonNull(name, "name is required.").trim();
        this.name = name;
    }

    public BufferedImage opposite() {
        return opposite;
    }


    public boolean isExpired() {
        LocalDate now = LocalDate.now();
        return now.isBefore(expiryOn) && now.isAfter(issueOn);
    }

    public LocalDate expiryOn() {
        return expiryOn;
    }


    public LocalDate issueOn() {
        return issueOn;
    }

    public String issuer() {
        return issuer;
    }

    public BufferedImage positive() {
        return positive;
    }

    public String number() {
        return number;
    }

    protected void setNumber(String number) {
        number = Objects.requireNonNull(number, "number is required").trim();
        this.number = number;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", License.class.getSimpleName() + "[", "]")
                .add("positive=" + positive)
                .add("expiryOn=" + expiryOn)
                .add("issueOn=" + issueOn)
                .add("issuer='" + issuer + "'")
                .add("opposite=" + opposite)
                .add("number='" + number + "'")
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        License license = (License) o;

        if (expiryOn != null ? !expiryOn.equals(license.expiryOn) : license.expiryOn != null) return false;
        if (issueOn != null ? !issueOn.equals(license.issueOn) : license.issueOn != null) return false;
        if (issuer != null ? !issuer.equals(license.issuer) : license.issuer != null) return false;
        if (number != null ? !number.equals(license.number) : license.number != null) return false;
        return name != null ? name.equals(license.name) : license.name == null;
    }

    @Override
    public int hashCode() {
        int result = expiryOn != null ? expiryOn.hashCode() : 0;
        result = 31 * result + (issueOn != null ? issueOn.hashCode() : 0);
        result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
