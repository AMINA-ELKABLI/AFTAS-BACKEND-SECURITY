package ma.youcode.aftas.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.dto.CreateUpdateRankingDto;
import ma.youcode.aftas.exception.InvalidDataException;
import ma.youcode.aftas.exception.ResourceAlreadyExistsException;
import ma.youcode.aftas.exception.ResourceNotFoundException;
import ma.youcode.aftas.model.Competition;
import ma.youcode.aftas.model.Member;
import ma.youcode.aftas.model.RankId;
import ma.youcode.aftas.model.Ranking;
import ma.youcode.aftas.repository.RankingRepository;
import ma.youcode.aftas.service.ICompetitionService;
import ma.youcode.aftas.service.IMemberService;
import ma.youcode.aftas.service.IRankingService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements IRankingService {
    private final RankingRepository rankingRepository;
    private final IMemberService memberService;
    private final ICompetitionService competitionService;

    @Override
    public Ranking save(CreateUpdateRankingDto rankingDto) {
        Optional<Member> foundMemberOptional = memberService.findByNum(rankingDto.getMemberNum());
        if (foundMemberOptional.isEmpty()) {
            throw new InvalidDataException("this member doesn't exist added it first");
        } else {
            Optional<Competition> foundCompetition = competitionService.findByCode(rankingDto.getCompetitionCode());
            if (foundCompetition.isEmpty()) {
                throw new InvalidDataException("this competition doesn't exist added it first");
            } else {
                Optional<Ranking> foundRanking = rankingRepository.findByMemberAndCompetition(foundMemberOptional.get(), foundCompetition.get());
                System.out.println(foundRanking.isPresent());
                if (foundRanking.isPresent()) {
                    throw new ResourceAlreadyExistsException("this member is already in this competition");
                } else {
                    RankId rankId = RankId.builder()
                            .competitionCode(foundCompetition.get().getCode())
                            .memberNumber(foundMemberOptional.get().getNum())
                            .build();
                    Ranking addedRanking = new Ranking().builder().score(0D)
                            .rank(0)
                            .id(rankId)
                            .member(foundMemberOptional.get())
                            .competition(foundCompetition.get())
                            .build();
                    return rankingRepository.save(addedRanking);
                }

            }

        }

    }

    @Override
    public List<Ranking> getAllRankings(Pageable pageable) {
        return  rankingRepository.findAll(pageable).getContent();
    }

    @Override
    public Ranking updateRanking(Ranking ranking) {
        Ranking ranking1 =findRankingByMemberNumberAndCompetitionCode(ranking.getMember().getNum(), ranking.getCompetition().getCode());
        ranking1.setScore(ranking.getScore());
        ranking1= rankingRepository.save(ranking1);
        List<Ranking> rankings =updateAllRankings(ranking1);
        return  rankings.get(rankings.indexOf(ranking1));
    }

    @Override
    public Ranking findRankingByMemberNumberAndCompetitionCode(Integer number, String competitionCode) {
        RankId rankId = new RankId(number,competitionCode);
        return rankingRepository.findById(rankId).orElseThrow(()->new ResourceNotFoundException("Ranking does not exist"));
    }

    @Override
    public List<Ranking> updateAllRankings(Ranking ranking) {
        List<Ranking> rankings = rankingRepository.findAllByCompetitionCodeOrderByScoreDesc(ranking.getCompetition().getCode(),Pageable.unpaged()).getContent();
        List<Ranking> rankings1 = new ArrayList<>();
        for(Ranking ranking1 : rankings){
            ranking1.setRank(rankings.indexOf(ranking1)+1);
            rankings1.add(ranking1);
        }
        rankingRepository.saveAll(rankings1);
        return rankings1;
    }




}
