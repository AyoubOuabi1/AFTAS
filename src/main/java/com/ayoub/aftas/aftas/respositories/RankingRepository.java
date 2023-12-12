package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
     Ranking getRankingByCompetition_idAndMember_id(Long Competition_id,Long Member_id);
}
