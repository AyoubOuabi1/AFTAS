package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.dto.AuthenticateDto;
import com.ayoub.aftas.aftas.dto.RegisterDto;
import com.ayoub.aftas.aftas.dto.ResponseDto;
import com.ayoub.aftas.aftas.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ayoub.aftas.aftas.auth.config.JwtService;
import com.ayoub.aftas.aftas.mappers.UserMapper;
import com.ayoub.aftas.aftas.respositories.UserRepository;
import com.ayoub.aftas.aftas.entities.*;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleServiceImp roleService;
    private final UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) throws EntityNotFoundException {
        User user = this.userRepository.findByEmail(username).get();
        if(user==null) throw new EntityNotFoundException("user not found !");
        return user;
    }
    @Override
    public ResponseDto register(RegisterDto registerDto) throws ValidationException {
        Optional<User> existingUser = this.userRepository.findByEmail(registerDto.getEmail());
        if(existingUser.isPresent()) throw new ValidationException("This email already exists !");
        RoleEntity userRole = roleService.getRoleByName("USER");

        User user = User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .roles(Collections.singleton(userRole))
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        ResponseDto responseDto = userMapper.mapUserToResponseDTO(this.userRepository.save(user));
        responseDto.setAccessToken(jwtService.generateToken(user));
        return responseDto;
    }

    @Override
    public ResponseDto authenticate(AuthenticateDto authenticateDto){
        this.authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(authenticateDto.getEmail(), authenticateDto.getPassword())
        );
        User user = this.findUserByUsername(authenticateDto.getEmail());
        ResponseDto responseDto = userMapper.mapUserToResponseDTO(user);
        responseDto.setAccessToken(jwtService.generateToken(user));

        return responseDto;
    }


}
