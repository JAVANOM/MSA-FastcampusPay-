package com.fastcampuspay.banking.adapter.in.web;


import com.fastcampuspay.banking.application.port.in.RequestFirmbankingCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingUseCase;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import com.fastcampuspay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
// 외부와 상호작용(http 첫 )
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase requestFirmbankingUseCase;


    @PostMapping(path="/banking/firmbanking/request")
    FirmbankingRequest firmbankingRequest(@RequestBody RequestFirmbankingRequest request)
    {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .fromBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .toBankName(request.getToBankName())
                .moneyAmount(request.getMoneyAmount())
                .build();
        //useCase
        return requestFirmbankingUseCase.requestFirmbanking(command);

    }


}
