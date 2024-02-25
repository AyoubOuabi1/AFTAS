package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.RefreshToken;
import com.ayoub.aftas.aftas.entities.User;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    Optional<RefreshToken> findByToken(String token);

    RefreshToken findByUser(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);
}
