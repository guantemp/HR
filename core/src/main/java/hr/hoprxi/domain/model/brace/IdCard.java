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
package hr.hoprxi.domain.model.brace;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * @author <a href="www.hoprxi.com/authors/guan xianghuang">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2019-01-14
 */
public class IdCard {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|31)|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}([0-9]|x|X)$");
    private long readerID;
    private String number;
    private String name;
    private Nation nation;
    private Address address;

    public IdCard(String number, long readerID, String issuer, LocalDate issuerOn, LocalDate expiryOn, BufferedImage positive, BufferedImage opposite,
                  Nation nation, Address address) {
        this.readerID = readerID;
        this.nation = nation;
        this.address = address;
    }

    public Gender gender() {
        return Gender.MALE;
    }

    public Nation nation() {
        return nation;
    }

    public enum Gender {
        MALE, FEMALE
    }

    private void setNumber(String number) {
        number = Objects.requireNonNull(number, "number is required");
        Matcher matcher = NUMBER_PATTERN.matcher(number);
        if (!matcher.matches())
            throw new IllegalArgumentException("Non-conforming ID number.");
        this.number = number;
    }

    public LocalDate born() {
        return null;
    }

    public enum Nation {
        HAN, BUYI, MENG_GU, HUI, ZANG
    }
}
