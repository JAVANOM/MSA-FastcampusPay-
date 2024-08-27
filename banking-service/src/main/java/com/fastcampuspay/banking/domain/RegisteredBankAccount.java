package com.fastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {

    @Getter private final String registeredBankingAccountId;

    @Getter private final String membershipId;

    @Getter private final String bankName;

    @Getter private final String bankAccountNumber;

    @Getter private final Boolean linkedStatusIsValid;

    public static RegisteredBankAccount generateRegisteredBankAccount(
            RegisteredBankAccount.RegisteredBankAccountId registeredBankingAccountId,
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
            ) {
        return new RegisteredBankAccount(
                registeredBankingAccountId.registeredBankingAccountId,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid);
    }

    //싱글톤
    @Value
    public static class RegisteredBankAccountId{

        public RegisteredBankAccountId(String value){

            this.registeredBankingAccountId = value;
        }
        String registeredBankingAccountId;

    }

    @Value
    public static class MembershipId{

        public MembershipId(String value){

            this.membershipId = value;
        }
        String membershipId;

    }

    @Value
    public static class BankName{

        public BankName(String value){

            this.bankName = value;
        }
        String bankName;

    }

    @Value
    public static class BankAccountNumber{

        public BankAccountNumber(String value){

            this.bankAccountNumber = value;
        }
        String bankAccountNumber;

    }

    @Value
    public static class LinkedStatusIsValid{

        public LinkedStatusIsValid(boolean value){

            this.linkedStatusIsValid = value;
        }
        boolean linkedStatusIsValid;

    }
}
