package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting,Long> {

    @Query()
    Hunting findByUser_IdAndCompetition_IdAndFish_Id(Long Member_Id, Long Competition_Id,Long Fish_Id);
}
