package com.member.cozastore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "verificationtoken")
public class VerificationTokenEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @OneToOne()
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @Getter
    @Setter
    private UserEntity user;

    @Column(name = "token")
    @Getter
    @Setter
    private String token;

    @Column(name = "expired_date")
    @Getter
    @Setter
    private Date expiredDate;
}
