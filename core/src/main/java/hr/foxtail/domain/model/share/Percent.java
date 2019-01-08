/*
 * Percent.java
 *
 * Copyright 2016 www.foxtail.cc rights Reserved.
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
package hr.foxtail.domain.model.share;

import java.math.BigDecimal;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月23日
 */
public final class Percent {
    private Investor Investor;
    private BigDecimal percent;

    /**
     * @param investor
     * @param percent
     */
    public Percent(cc.foxtail.investor.domain.model.share.Investor investor, BigDecimal percent) {
        super();
        setInvestor(investor);
        setPercent(percent);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Percent other = (Percent) obj;
        if (Investor == null) {
            if (other.Investor != null)
                return false;
        } else if (!Investor.equals(other.Investor))
            return false;
        if (percent == null) {
            if (other.percent != null)
                return false;
        } else if (!percent.equals(other.percent))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Investor == null) ? 0 : Investor.hashCode());
        result = prime * result + ((percent == null) ? 0 : percent.hashCode());
        return result;
    }

    public Investor investor() {
        return Investor;
    }

    public BigDecimal percent() {
        return percent;
    }

    protected void setInvestor(Investor investor) {
        Investor = investor;
    }

    protected void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Percent [Investor=" + Investor + ", percent=" + percent + "]";
    }

}
