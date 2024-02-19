package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.AuthenticateDto;
import com.ayoub.aftas.aftas.dto.RegisterDto;
import com.ayoub.aftas.aftas.dto.ResponseDto;
import com.ayoub.aftas.aftas.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User findUserByUsername(String username);
    public ResponseDto register(RegisterDto registerDto);
    public ResponseDto authenticate(AuthenticateDto authenticateDto);
}
