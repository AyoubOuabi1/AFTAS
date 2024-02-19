package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.PermissionEntity;
import com.ayoub.aftas.aftas.services.PermissionService;
import org.springframework.stereotype.Service;
import com.ayoub.aftas.aftas.respositories.PermissionRepository;

@Service
public class PermissionServiceImp implements PermissionService {


    private final PermissionRepository permissionRepository;

    public PermissionServiceImp(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }
    @Override
    public PermissionEntity getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public PermissionEntity savePermission(PermissionEntity permission) {
        return permissionRepository.save(permission);
    }

}
