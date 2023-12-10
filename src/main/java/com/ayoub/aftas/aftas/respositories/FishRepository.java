package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish,Long> {
}
