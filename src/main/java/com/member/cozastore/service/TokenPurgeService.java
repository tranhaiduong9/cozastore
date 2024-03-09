package com.member.cozastore.service;

import com.member.cozastore.repository.VerificationTokenRepository;
import com.member.cozastore.service.imp.TokenPurgeServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokenPurgeService implements TokenPurgeServiceImp {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpiredToken() {
        Date now = Date.from(Instant.now());
        verificationTokenRepository.deleteByExpiredDateLessThan(now);
    }
}
