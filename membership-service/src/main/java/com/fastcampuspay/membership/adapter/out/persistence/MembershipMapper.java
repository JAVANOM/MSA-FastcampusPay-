package com.fastcampuspay.membership.adapter.out.persistence;

import com.fastcampuspay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipJpaEntity membershipJapEntity){
        return Membership.generateMember(
                new Membership.MembershipId(membershipJapEntity.getMembershipId()+""),
                new Membership.MembershipName(membershipJapEntity.getName()),
                new Membership.MembershipEmail(membershipJapEntity.getEmail()),
                new Membership.MembershipAddress(membershipJapEntity.getAddress()),
                new Membership.MembershipIsValid(membershipJapEntity.isValid()),
                new Membership.MembershipIsCorp(membershipJapEntity.isCorp())

        );
    }


}
