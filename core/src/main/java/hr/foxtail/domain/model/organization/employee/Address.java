/*
 * Copyright (c) 2018. www.foxtail.cc rights Reserved.
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
 *
 *
 */
package hr.foxtail.domain.model.organization.employee;

import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * @author <a href="www.foxtail.cc/authors/guan xianghuang">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2018-07-19
 */
public final class Address {
    private static final Pattern POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");
    //such as luzhou
    private String city;
    //such as sichuan
    private String province;
    //such as 614000
    private String postcode;
    //such as lonmatan,luxiang
    private String county;
    //such as xiaoshi street
    private String street;
    private String details;

    public Address(String province, String city, String county, String street, String details, String postcode) {
        setProvince(province);
        setCity(city);
        setCounty(county);
        setStreet(street);
        setDetails(details);
        setPostcode(postcode);
    }

    private void setProvince(String province) {
        this.province = province;
    }

    private void setCounty(String county) {
        this.county = county;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setDetails(String details) {
        this.details = details;
    }

    private void setCity(String city) {
        Objects.requireNonNull(city, "city required");
        this.city = city;
    }

    private void setPostcode(String postcode) {
        postcode = Objects.requireNonNull(postcode, "postcode required").trim();
        Matcher matcher = POSTCODE_PATTERN.matcher(postcode);
        if (!matcher.matches())
            throw new IllegalArgumentException("Non-conforming Postal Code Format.");
        this.postcode = postcode;
    }

    public String city() {
        return city;
    }

    public String postcode() {
        return postcode;
    }

    public Locale locale() {
        return Locale.CHINA;
    }

    public String province() {
        return province;
    }

    public String county() {
        return county;
    }

    public String street() {
        return street;
    }

    public String details() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (province != null ? !province.equals(address.province) : address.province != null) return false;
        if (postcode != null ? !postcode.equals(address.postcode) : address.postcode != null) return false;
        if (county != null ? !county.equals(address.county) : address.county != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return details != null ? details.equals(address.details) : address.details == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("city='" + city + "'")
                .add("province='" + province + "'")
                .add("postcode='" + postcode + "'")
                .add("county='" + county + "'")
                .add("street='" + street + "'")
                .add("details='" + details + "'")
                .toString();
    }
}
