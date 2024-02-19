package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.services.RoleService;
import org.springframework.stereotype.Service;
import com.ayoub.aftas.aftas.respositories.RoleRepository;
import com.ayoub.aftas.aftas.entities.RoleEntity;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }




}
