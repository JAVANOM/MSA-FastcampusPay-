package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

     RegisteredBankAccountJpaEntity createRegisterBankAccount(
             RegisteredBankAccount.MembershipId membershipId,
             RegisteredBankAccount.BankName bankName,
             RegisteredBankAccount.BankAccountNumber bankAccountNumber,
             RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
     );
}