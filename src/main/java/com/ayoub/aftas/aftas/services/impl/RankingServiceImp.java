package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.Hunting.HuntingInternalServerError;
import com.ayoub.aftas.aftas.dto.RankingDto;
import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.entities.Member;
import com.ayoub.aftas.aftas.entities.Ranking;
import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
import com.ayoub.aftas.aftas.mappers.MemberMapper;
import com.ayoub.aftas.aftas.respositories.RankingRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import com.ayoub.aftas.aftas.services.MemberService;
import com.ayoub.aftas.aftas.services.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImp implements RankingService {

    RankingRepository rankingRepository;
    MemberService memberService;
    CompetitionService competitionService;

    public RankingServiceImp(RankingRepository rankingRepository,MemberService memberService,CompetitionService competitionService) {
        this.rankingRepository = rankingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
    }

    @Override
    public Ranking save(RankingDto rankingDto) {
        Competition competition= CompetitionMapper.mapFromDto(
                competitionService.getById(rankingDto.getCompetitionId())
        );

        if(getAll().size()<competition.getNumberOfParticipants()) {
            Member member=MemberMapper.mapFromDto(
                    memberService.getById(rankingDto.getMemberId())
            );
            Ranking ranking=Ranking.builder()
                    .rank(0)
                    .score(0)
                    .competition(competition)
                    .member(member)
                    .build();
            ranking.setScore(0);
            ranking.setRank(0);
            return rankingRepository.save(ranking);
        }else {
            throw new HuntingInternalServerError("you can't add participants to this competition");
        }
    }

    @Override
    public Ranking update(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public void delete(Ranking ranking) {
        rankingRepository.delete(ranking);
    }

    @Override
    public List<Ranking> getAll() {
        return rankingRepository.findAll();
    }

    @Override
    public Ranking getById(Long id) {
        return rankingRepository.findById(id).get();
    }

    @Override
    public Ranking getRankingByCompetition_idAndMember_id(Long competitionId, Long memberId) {
        return rankingRepository.getRankingByCompetition_idAndMember_id(competitionId, memberId);
    }
}
