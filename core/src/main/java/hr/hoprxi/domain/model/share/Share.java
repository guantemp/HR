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

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月23日
 */
public final class Share {
    private Investor investor;
    private long quantity;

    public Share(Investor investor, long quantity) {
        super();
        setInvestor(investor);
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Share other = (Share) obj;
        if (investor == null) {
            if (other.investor != null)
                return false;
        } else if (!investor.equals(other.investor))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((investor == null) ? 0 : investor.hashCode());
        result = prime * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }

    public Investor investor() {
        return investor;
    }

    public long quantity() {
        return quantity;
    }

    protected void setInvestor(Investor investor) {
        this.investor = investor;
    }

    @Override
    public String toString() {
        return "Share [investor=" + investor + ", quantity=" + quantity + "]";
    }

}
