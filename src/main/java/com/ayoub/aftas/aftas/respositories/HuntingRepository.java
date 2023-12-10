package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting,Long> {
}
