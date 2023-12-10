package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish,Long> {
}
