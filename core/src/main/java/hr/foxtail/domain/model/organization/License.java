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

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public class License {
    private Type type;
    private BufferedImage positive;
    private LocalDate expiryOn;
    private LocalDate issueOn;
    private String issuer;
    private BufferedImage opposite;
    private String number;
    /**
     * @param type
     * @param number
     */
    public License(Type type, String number) {
        this(type, number, null, null, null, null, null);
    }

    public License(Type type, String number, String issuer, LocalDate issuerOn, LocalDate expiryOn, BufferedImage positive, BufferedImage opposite) {
        setType(type);
        setNumber(number);
        this.issuer = issuer;
        this.issueOn = issuerOn;
        this.expiryOn = expiryOn;
        this.positive = positive;
        this.opposite = opposite;
    }

    public BufferedImage opposite() {
        return opposite;
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
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }

    public boolean isExpired() {
        LocalDate now = LocalDate.now();
        return now.isBefore(expiryOn) && now.isAfter(issueOn);
    }

    public LocalDate expiryOn() {
        return expiryOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    public Type name() {
        return type;
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

    protected void setType(Type type) {
        Objects.requireNonNull(type, "type is required.");
        this.type = type;
    }

    protected void setNumber(String number) {
        number = Objects.requireNonNull(number, "number is required").trim();
        this.number = number;
    }

    @Override
    public String toString() {
        return "License [type=" + type + ", number=" + number + ", issuer=" + issuer + ", issueOn=" + issueOn
                + ", expiryOn=" + expiryOn + ", positive=" + positive + "]";
    }

    public enum Type {
        IDCARD, PASSPORT
    }
}
