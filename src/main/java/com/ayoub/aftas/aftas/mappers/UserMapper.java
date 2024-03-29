package com.ayoub.aftas.aftas.mappers;

import com.ayoub.aftas.aftas.dto.AuthResponse;
import com.ayoub.aftas.aftas.dto.UserDto;
import com.ayoub.aftas.aftas.entities.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public AuthResponse mapUserToResponseDTO(User user) {
        Set<String> permissionNames = user.getPermissions().stream()
                .map(PermissionEntity::getName)
                .collect(Collectors.toSet());

        return new AuthResponse(
                user.getUsername(),
                user.getEmail(),
                null,
                null,
                user.getRoles().stream().findFirst().map(RoleEntity::getName).orElse(null),
                user.isActive(),

                permissionNames
        );
    }
    public  UserDto toDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setNum(user.getNum());
        userDTO.setName(user.getName());
        userDTO.setActive(user.isActive());
        userDTO.setFamilyName(user.getFamilyName());
        userDTO.setAccessionDate(user.getAccessionDate());
        userDTO.setNationality(user.getNationality());
        userDTO.setIdentityDocument(user.getIdentityDocument());
        userDTO.setIdentityNumber(user.getIdentityNumber());
        userDTO.setRole(user.getRoles().stream().findFirst().map(RoleEntity::getName).orElse(null));
        return userDTO;
    }

    public   User toEntity(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setNum(userDTO.getNum());
        user.setName(userDTO.getName());
        user.setActive(userDTO.isActive());
        user.setFamilyName(userDTO.getFamilyName());
        user.setAccessionDate(userDTO.getAccessionDate());
        user.setNationality(userDTO.getNationality());
        user.setIdentityDocument(userDTO.getIdentityDocument());
        user.setIdentityNumber(userDTO.getIdentityNumber());
        return user;
    }


}
