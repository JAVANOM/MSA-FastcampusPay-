package com.fastcampuspay.money.application.service;


import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.fastcampuspay.money.application.port.in.IncreseMoneyRequestCommand;
import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase
{
    private final IncreaseMoneyPort increaseMoneyPort;

    private final MoneyChangingRequestMapper mapper; //entity로 만들기 위해

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreseMoneyRequestCommand command) {

        // 머니의 충전, 증액이라는 과정
        // 1. 고객정보가 정상인지 확인(멤버)

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인(뱅킹)

        // 3. 법인 계좌 상태도 정상인지 확인(뱅킹)

        // 4. 증액을 위한 "기록" -> 요청상태로 MoneyChangingRequest 를 생성 (MoneyChangingRequest)

        // 5. 펌뱅킹 수행 (고객의 연동된 계좌 -> 패캠페이 법인 계좌)(뱅킹)

        // 6-1. 결과가 정산적이라면, 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
        // 성공 시에 멤버의 MemberMoney 값의 증액이 필요

        MemberMoneyJpaEntity memberMoneyJpaEntity= increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount());

        if(memberMoneyJpaEntity != null){
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.MoneyChangingType(1),
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.MoneyChangingStatus(1),
                    new MoneyChangingRequest.Uuid(UUID.randomUUID())

                    )
            );

        }

        // 6-2. 결과 실패하면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        return null;

    }
}
