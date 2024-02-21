package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.entities.RankId;
import com.ayoub.aftas.aftas.entities.Ranking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {
     Ranking getRankingByCompetition_idAndUser_id(Long Competition_id,Long Member_id);

     List<Ranking> findRankingByCompetition_Id(Long competitionId, Sort sort);

     List<Ranking> findRankingByCompetition_Id(Long competitionId);
}
