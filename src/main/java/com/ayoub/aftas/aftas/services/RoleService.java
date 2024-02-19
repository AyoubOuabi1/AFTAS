package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleEntity getRoleByName(String name);
    RoleEntity saveRole(RoleEntity role);
}
