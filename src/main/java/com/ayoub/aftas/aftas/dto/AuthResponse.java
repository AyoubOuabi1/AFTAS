package com.ayoub.aftas.aftas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
    private String role;
    private boolean isActive;
    private Set<String> permissions;
}
