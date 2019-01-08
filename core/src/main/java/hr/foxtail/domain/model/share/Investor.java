/*
 * InvestorMember.java
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

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月9日
 */
public final class Investor {
    private InvestorType investorType;
    private long personOrOrganizationId;

    /**
     * @param investorType
     * @param personOrOrganizationId
     */
    public Investor(InvestorType investorType, long personOrOrganizationId) {
        super();
        setInvestorType(investorType);
        this.personOrOrganizationId = personOrOrganizationId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Investor other = (Investor) obj;
        if (personOrOrganizationId != other.personOrOrganizationId)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (personOrOrganizationId ^ (personOrOrganizationId >>> 32));
        return result;
    }

    public InvestorType investorType() {
        return investorType;
    }

    public long personOrOrganizationId() {
        return personOrOrganizationId;
    }

    protected void setInvestorType(InvestorType investorType) {
        if (null == investorType)
            throw new IllegalArgumentException("Must provide a person or organization type.");
        this.investorType = investorType;
    }
}
