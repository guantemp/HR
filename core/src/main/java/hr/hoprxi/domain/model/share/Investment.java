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
package hr.hoprxi.domain.model.share;

import javax.money.MonetaryAmount;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月24日
 */
public final class Investment {
    private Investor Investor;
    private MonetaryAmount money;

    /**
     * @param investor
     * @param money
     */
    public Investment(Investor investor, MonetaryAmount money) {
        super();
        setInvestor(investor);
        setMoney(money);
    }

    public Investor investor() {
        return Investor;
    }

    public MonetaryAmount money() {
        return money;
    }

    protected void setInvestor(Investor investor) {
        Investor = investor;
    }

    protected void setMoney(MonetaryAmount money) {
        this.money = money;
    }
}
