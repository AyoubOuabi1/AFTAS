package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.RefreshToken;
import com.ayoub.aftas.aftas.entities.User;
import com.ayoub.aftas.aftas.respositories.RefreshTokenRepository;
import com.ayoub.aftas.aftas.services.RefreshTokenService;
import com.ayoub.aftas.aftas.services.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImp implements RefreshTokenService {

    Long refreshTokenDurationMs = 600000L;
    RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenServiceImp(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
