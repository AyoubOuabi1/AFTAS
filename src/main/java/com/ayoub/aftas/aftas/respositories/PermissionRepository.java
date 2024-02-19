package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    PermissionEntity findByName(String name);

    void deleteByName(String name);
}
