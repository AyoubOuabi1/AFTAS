package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    @Query("SELECT COUNT(c) FROM Competition c")
    int  countCompetitions();

}
