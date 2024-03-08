package com.member.cozastore.repository;

import com.member.cozastore.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Integer> {
    @Query("SELECT v FROM verificationtoken v WHERE v.token = :token")
    VerificationTokenEntity findByToken(String token);

    void deleteByExpiredDateLessThan(Date now);
}
