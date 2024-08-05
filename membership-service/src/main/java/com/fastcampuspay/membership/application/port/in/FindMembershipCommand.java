package com.fastcampuspay.membership.application.port.in;


import common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper=false) // super를 포함 할건지
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

    @NotNull
    private final String membershipId;

}
