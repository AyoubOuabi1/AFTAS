package com.ayoub.aftas.aftas.services.impl;

 import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
 import com.ayoub.aftas.aftas.dto.RankingDto;
 import com.ayoub.aftas.aftas.dto.UserDto;
 import com.ayoub.aftas.aftas.entities.Competition;
 import com.ayoub.aftas.aftas.entities.RankId;
 import com.ayoub.aftas.aftas.entities.Ranking;
 import com.ayoub.aftas.aftas.entities.User;
 import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
 import com.ayoub.aftas.aftas.mappers.UserMapper;
 import com.ayoub.aftas.aftas.respositories.RankingRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
 import com.ayoub.aftas.aftas.services.RankingService;
 import com.ayoub.aftas.aftas.services.UserService;
 import org.springframework.data.domain.Sort;
 import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class RankingServiceImp implements RankingService {

    RankingRepository rankingRepository;
    UserMapper userMapper;
    UserService userService;
    CompetitionService competitionService;

    public RankingServiceImp(RankingRepository rankingRepository,
                             UserMapper userMapper,
                             UserService userService,
                             CompetitionService competitionService) {
        this.rankingRepository = rankingRepository;
        this.userService = userService;
        this.competitionService = competitionService;
        this.userMapper = userMapper;
    }

    @Override
    public Ranking save(RankingDto rankingDto) {
        Competition competition= CompetitionMapper.mapFromDto(
                competitionService.getById(rankingDto.getCompetitionId())
        );

        User user=userMapper.toEntity(
                userService.getById(rankingDto.getUserId())
        );
        if(getRankingByCompetition_idAndMember_id(competition.getId(), user.getId()) == null) {
            if (findRankingByCompetition_Id(competition.getId()).size() <= competition.getNumberOfParticipants()) {

                Ranking ranking = Ranking.builder()
                        .id(RankId.builder()
                                .userId(user.getId())
                                .competitionId(competition.getId())
                                .build())
                        .rank(0)
                        .score(0)
                        .competition(competition)
                        .user(user)
                        .build();
                ranking.setScore(0);
                ranking.setRank(0);
                return rankingRepository.save(ranking);
            } else {
                throw new InternalServerError("you can't add participants to this competition because its Full");
            }
        }else {
            throw new InternalServerError("you can't add this member to this competition because this member is already exist");
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
    public Ranking getById(RankId id) {

        return rankingRepository.findById(id).get();
    }

    @Override
    public Ranking getRankingByCompetition_idAndMember_id(Long competitionId, Long memberId) {
        return rankingRepository.getRankingByCompetition_idAndUser_id(competitionId, memberId);
    }

    @Override
    public List<Ranking> findRankingByCompetition_Id(Long competition_id) {
        Sort sort = Sort.by(Sort.Order.desc("score"));
        if(!getAll().isEmpty()){
            Long competitionId=getAll().get(0).getId().getCompetitionId();
            if(competition_id==null){
                return rankingRepository.findRankingByCompetition_Id(competitionId, sort);
            }
        }

        return rankingRepository.findRankingByCompetition_Id(competition_id, sort);
    }

    @Override
    public  List<UserDto> getWinners(Long id) {
        List<UserDto> winners = new ArrayList<>();
        List<Ranking> rankings =findRankingByCompetition_Id(id);
        winners.add(userMapper.toDTO(rankings.get(0).getUser()));
        winners.add(userMapper.toDTO(rankings.get(1).getUser()));
        winners.add(userMapper.toDTO(rankings.get(2).getUser()));
        return winners;
    }

    private List<Ranking> sortByScore(Long competitionId){
          List<Ranking> rankingList=findRankingByCompetition_Id(competitionId).stream()
                .sorted(Comparator.comparingInt(Ranking::getScore)).
                toList();
        IntStream.range(0, rankingList.size())
                .forEach(i ->{
                    Ranking ranking = rankingList.get(i);
                    ranking.setRank(i+1);
                    rankingRepository.save(ranking);
                });
          return rankingList;
    }
}
