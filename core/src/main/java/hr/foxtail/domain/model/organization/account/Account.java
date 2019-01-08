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
package hr.foxtail.domain.model.organization.account;

import java.util.Objects;
import java.util.StringJoiner;

/***
 * @author <a href="www.foxtail.cc/authors/guan xiangHuan">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2019-01-07
 */
public final class Account {
    private String accountNumber;
    private String accountName;
    private Bank bankOfDeposit;

    public Account(Bank bankOfDeposit, String accountName, String accountNumber) {
        setBankOfDeposit(bankOfDeposit);
        setAccountName(accountName);
        setAccountNumber(accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountNumber != null ? !accountNumber.equals(account.accountNumber) : account.accountNumber != null)
            return false;
        if (accountName != null ? !accountName.equals(account.accountName) : account.accountName != null) return false;
        return bankOfDeposit == account.bankOfDeposit;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("accountNumber='" + accountNumber + "'")
                .add("accountName='" + accountName + "'")
                .add("bankOfDeposit=" + bankOfDeposit)
                .toString();
    }

    @Override
    public int hashCode() {
        int result = accountNumber != null ? accountNumber.hashCode() : 0;
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
        result = 31 * result + (bankOfDeposit != null ? bankOfDeposit.hashCode() : 0);
        return result;
    }

    public String accountNumber() {
        return accountNumber;
    }

    public String accountName() {
        return accountName;
    }

    public Bank bankOfDeposit() {
        return bankOfDeposit;
    }

    private void setAccountNumber(String accountNumber) {
        this.accountNumber = Objects.requireNonNull(accountNumber, "account number is required");
    }

    private void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    private void setBankOfDeposit(Bank bankOfDeposit) {
        this.bankOfDeposit = bankOfDeposit;
    }
}
