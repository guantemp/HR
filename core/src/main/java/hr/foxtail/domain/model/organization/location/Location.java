/*
 * Copyright 2019 www.foxtail.cc All rights Reserved.
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
package hr.foxtail.domain.model.organization.location;

import java.util.Locale;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public final class Location {
    private GCJ02 gcj02;
    // such as Chinese
    private Locale locale;
    //such as luzhou
    private String city;
    //such as sichuan
    private String province;
    //such as 614000
    private String zipCode;
    //such as lonmatan,luxian
    private String county;
    //such as 中大街
    private String street;
    private String houseNumber;

    public Location(Locale locale, String province, String city, String county, String street, String houseNumber, String zipCode, GCJ02 gcj02) {
        this.gcj02 = gcj02;
        this.locale = locale;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.county = county;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Location(String province, String city, String county, String street, String houseNumber, String zipCode, GCJ02 gcj02) {
        this(Locale.getDefault(), province, city, county, street, houseNumber, zipCode, gcj02);
    }
}
