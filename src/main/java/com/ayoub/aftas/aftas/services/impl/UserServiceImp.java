package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.*;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public AuthResponse register(RequestRegisterDto requestRegisterDto) throws ValidationException {
        Optional<User> existingUser = this.userRepository.findByEmail(requestRegisterDto.getEmail());
        if(existingUser.isPresent()) throw new ValidationException("This email already exists !");
        RoleEntity userRole = roleService.getRoleByName("USER");

        User user = User.builder()
                .username(requestRegisterDto.getUsername())
                .email(requestRegisterDto.getEmail())
                .num(requestRegisterDto.getNum())
                .name(requestRegisterDto.getName())
                .familyName(requestRegisterDto.getFamilyName())
                .accessionDate(requestRegisterDto.getAccessionDate())
                .identityDocument(requestRegisterDto.getIdentityDocument())
                .identityNumber(requestRegisterDto.getIdentityNumber())
                .nationality(requestRegisterDto.getNationality())
                .roles(Collections.singleton(userRole))
                .password(passwordEncoder.encode(requestRegisterDto.getPassword()))
                .build();
        AuthResponse authResponse = userMapper.mapUserToResponseDTO(this.userRepository.save(user));
        authResponse.setAccessToken(jwtService.generateToken(user));
        return authResponse;
    }

    @Override
    public AuthResponse authenticate(AuthenticateDto authenticateDto){
        this.authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(authenticateDto.getEmail(), authenticateDto.getPassword())
        );
        User user = this.findUserByUsername(authenticateDto.getEmail());
        AuthResponse authResponse = userMapper.mapUserToResponseDTO(user);
        authResponse.setAccessToken(jwtService.generateToken(user));

        return authResponse;
    }
    @Override
    public List<UserDto> getAll() {
        List<UserDto> list = new ArrayList<>();
        userRepository.findAll().forEach(user->{
            list.add(userMapper.toDTO(user));
        });
        return  list;
    }

    @Override
    public UserDto getById(Long id)  {
        if(id != null){
            Optional<User> user= userRepository.findById(id);
            if(user.isPresent()){
                return userMapper.toDTO(user.get());
            }else {
                throw  new InternalServerError("Could not find member " + id);
            }
        }else {
            throw  new NotFoundException("Id Could not be null ");
        }

    }

    @Override
    public List<UserDto> findMembersNotRankedInCompetition(Long competitionId) {
        List<UserDto> list = new ArrayList<>();
        userRepository.findUserNotRankedInCompetition(competitionId).forEach(user->{
            list.add(userMapper.toDTO(user));
        });
        return  list;
    }

    @Override
    public List<UserDto> findMembersRankedInCompetition(Long competitionId) {
        List<UserDto> list = new ArrayList<>();
        userRepository.findUserRankedInCompetition(competitionId).forEach(user->{
            list.add(userMapper.toDTO(user));
        });
        return  list;
    }

    @Override
    public UserDto update(Long id,String Role) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().getRoles().clear();
            user.get().getRoles().add(roleService.getRoleByName(Role));
            return userMapper.toDTO(userRepository.save(user.get()));
        }
        return null;
    }

}
