package com.ayoub.aftas.aftas.mappers;

import com.ayoub.aftas.aftas.dto.ResponseDto;
import com.ayoub.aftas.aftas.entities.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public ResponseDto mapUserToResponseDTO(User user) {
        Set<String> permissionNames = user.getPermissions().stream()
                .map(PermissionEntity::getName)
                .collect(Collectors.toSet());

        return new ResponseDto(
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRoles().stream().findFirst().map(RoleEntity::getName).orElse(null),
                permissionNames
        );
    }
}
