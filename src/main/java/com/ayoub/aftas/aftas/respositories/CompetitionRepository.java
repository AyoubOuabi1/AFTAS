package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    @Query("SELECT c FROM Competition c where c.status=:status ")
    List<Competition> getCompetitionsByStatus(@Param("status") String status);

    @Query("SELECT c FROM Competition c where c in (SELECT r.competition FROM Ranking r where r.user.id=:userId)")
    Page<Competition> findCompetitionByUserId(Long userId, Pageable pageable);


    Page<Competition> findByStatus(String status, Pageable pageable);


}
