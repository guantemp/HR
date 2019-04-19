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

package hr.foxtail.domain.model.brace;

import java.util.regex.Pattern;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-14
 */
public class Person {
    private String weChat;
    private static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile("^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    private String qq;
    private String mobilePhone;
    private String email;
    private Education education;
    private Marriage marriage;

    public enum Education {
        PRIMARY_SCHOOL, JUNIOR_MIDDLE_SCHOOL, HIGHT_SCHOOL, JUNIOR_DEGREE, BACHELOR_DEGREE, MASTER, DOCTOR, OTHER
    }

    public enum Marriage {
        MARRIED, UNMARRIED
    }
}
