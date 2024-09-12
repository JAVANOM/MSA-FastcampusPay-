package com.fastcampuspay.money.adapter.out.persistence;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member_money")
public class MemberMoneyJpaEntity {

    @Id
    @GeneratedValue
    private Long membershipId;

    private String memberId;

    private int balance;

    public MemberMoneyJpaEntity(Long membershipId, int balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }
}
