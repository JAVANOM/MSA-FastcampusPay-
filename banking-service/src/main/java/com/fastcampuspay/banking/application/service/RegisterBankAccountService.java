package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.bank.BankAccount;
import com.fastcampuspay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import com.fastcampuspay.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase
{
    private final RegisterBankAccountPort registerBankAccountPort;

    private final RegisteredBankAccountMapper mapper; //entity로 만들기 위해

    private final RequestBankAccountInfoPort requestBankAccountInfoPort;


    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        //은행 계좌를 등록해야하는 서비스 bz

        // 1. 의부 실제 은행에 등록이 가능한 계좌인지 확인
        // 외부은행에 이 계좌 정상인지 ? 확인
        //biz -> Ex Sys
        // Port -> Adapter -> eEx SyS
        // Port 구현

        // 실제ㅇ 외부의 은행계좌 정보를 get
        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountInfoValid = accountInfo.isValid();
        // 2. 등록가능한 계좌라면, 등록 성공시 등록정보를 리턴
        // 2-1 등록 x 에러 리턴
        if(accountInfoValid){
            //등록 정보 저장
            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisterBankAccount(
                 new RegisteredBankAccount.MembershipId(command.getMembershipId() + ""),
                 new RegisteredBankAccount.BankName(command.getBankName()),
                 new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                 new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
            );

            return mapper.mapToDomainEntity(savedAccountInfo);


        }else {

        }

        return null;
    }
}
