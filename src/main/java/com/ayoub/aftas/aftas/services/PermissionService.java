package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.PermissionEntity;
import org.springframework.stereotype.Service;

@Service
public interface PermissionService {
    PermissionEntity getPermissionByName(String name);
    PermissionEntity savePermission(PermissionEntity permission);
}
