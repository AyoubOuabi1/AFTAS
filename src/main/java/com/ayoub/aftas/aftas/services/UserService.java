package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.*;
import com.ayoub.aftas.aftas.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

     User findUserByUsername(String username);
     User findByEmail(String username);
     AuthResponse register(RequestRegisterDto requestRegisterDto);
     AuthResponse authenticate(AuthenticateDto authenticateDto);

    List<UserDto> getAll();
    UserDto getById(Long id);

    List<UserDto> findMembersNotRankedInCompetition(Long competitionId);
    List<UserDto> findMembersRankedInCompetition(Long competitionId);

    UserDto update(Long id,String Role);

    AuthResponse getRefreshToken(String refreshTokenRequestDTO);

    Map<String,String> updateStatus(Long id);
}
