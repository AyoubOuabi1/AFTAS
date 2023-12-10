package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
