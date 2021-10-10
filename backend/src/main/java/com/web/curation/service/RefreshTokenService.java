package com.web.curation.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.user.RefreshToken;
import com.web.curation.repo.user.RefreshTokenRepo;
import com.web.curation.repo.user.UserRepo;

@Slf4j
@Service
public class RefreshTokenService {
    @Value("${webcuration.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    private UserRepo userRepo;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userEmail) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setExpirationDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(userRepo.findByUserEmail(userEmail));

        //refreshToken = refreshTokenRepo.save(refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirationDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
        }
        return token;
    }

    @Transactional
    public void deleteByUserId(Long userNo) {
        refreshTokenRepo.deleteById(userNo);
    }
}
