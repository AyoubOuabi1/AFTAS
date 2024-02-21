package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.RankingDto;
import com.ayoub.aftas.aftas.dto.UserDto;
import com.ayoub.aftas.aftas.entities.RankId;
import com.ayoub.aftas.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {
    Ranking save(RankingDto ranking);
    Ranking update(Ranking ranking);
    void delete(Ranking ranking);
    List<Ranking> getAll();
    Ranking getById(RankId id);
    Ranking getRankingByCompetition_idAndMember_id(Long competitionId, Long memberId);
    List<Ranking> findRankingByCompetition_Id(Long competitionId);
    List<UserDto> getWinners(Long Competition);
}
