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
package hr.hoprxi.domain.model.share;

import javax.money.MonetaryAmount;
import java.util.HashSet;
import java.util.Set;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月16日
 */
public final class InvestmentPie {
    private Set<Investment> investments = new HashSet<Investment>();
    private MonetaryAmount limit;
    private PercentPie percentPie;

    /**
     * @param limit
     * @param percentPie
     * @param investments
     */
    public InvestmentPie(MonetaryAmount limit, PercentPie percentPie, Set<Investment> investments) {
        super();
        this.limit = limit;
        this.percentPie = percentPie;
        this.investments = investments;
    }

    public Set<Investment> investments() {
        return investments;
    }

    public MonetaryAmount limit() {
        return limit;
    }

    public PercentPie percentPie() {
        return percentPie;
    }

    public void prorate(MonetaryAmount money) {

    }

    public void setInvestment(Set<Investment> investments) {
        this.investments = investments;
    }

    public void setLimit(MonetaryAmount limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "InvestmentPie [limit=" + limit + ", investments=" + investments + ", percentPie=" + percentPie + "]";
    }

    protected void setPercentPie(PercentPie percentPie) {
        this.percentPie = percentPie;
    }
}
