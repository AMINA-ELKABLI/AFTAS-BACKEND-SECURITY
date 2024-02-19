package ma.youcode.aftas.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.dto.CreateUpdateHuntingDto;
import ma.youcode.aftas.exception.FishWeightException;
import ma.youcode.aftas.exception.InvalidDataException;
import ma.youcode.aftas.exception.ResourceNotFoundException;
import ma.youcode.aftas.model.Fish;
import ma.youcode.aftas.model.Hunting;
import ma.youcode.aftas.model.Ranking;
import ma.youcode.aftas.repository.HuntingRepository;
import ma.youcode.aftas.service.IFishService;
import ma.youcode.aftas.service.IHuntingService;
import ma.youcode.aftas.service.IRankingService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements IHuntingService {
    private final HuntingRepository huntingRepository;
    private final IRankingService rankingService;
    private final IFishService fishService;
    @Override
    public Hunting createHunting(CreateUpdateHuntingDto huntingDto) {
        if(rankingService.findRankingByMemberNumberAndCompetitionCode(huntingDto.getMemberNum(), huntingDto.getCompetitionCode())==null){
            throw new InvalidDataException("This member is not signed this competition");
        }
        Ranking ranking = rankingService.findRankingByMemberNumberAndCompetitionCode(huntingDto.getMemberNum(), huntingDto.getCompetitionCode());

        if(ranking.getCompetition().getStartTime().isAfter(LocalTime.now())){
            throw new InvalidDataException("Competition has not started yet still some hours ");
        }
        if(ranking.getCompetition().getEndTime().isBefore(LocalTime.now())){
            throw new InvalidDataException("Competition has been finished");
        }
        Fish fish = fishService.findById(huntingDto.getFishId()).get();
        if(fish.getAverageWeight()>huntingDto.getWeight()){
            throw new FishWeightException("Fish weight is less than average weight");
        }
        if(existsHuntingByMemberNumberAndFishIdAndCompetitionCode(huntingDto.getMemberNum(),huntingDto.getFishId(),huntingDto.getCompetitionCode())){
            Hunting hunting1 = findHuntingByMemberNumberAndFishIdAndCompetitionCode(huntingDto.getMemberNum(),huntingDto.getFishId(),huntingDto.getCompetitionCode());
            ranking.setScore((ranking.getScore() + 2000));
            rankingService.updateRanking(ranking);
            return  huntingRepository.save(hunting1);
        }else{
            Hunting newHunting = Hunting.builder()
                    .member(ranking.getMember())
                    .competition(ranking.getCompetition())
                    .fish(fish)
                    .build();
            ranking.setScore(ranking.getScore() + 2000);
            rankingService.updateRanking(ranking);
            return huntingRepository.save(newHunting);
        }





    }

    @Override
    public Boolean existsHuntingByMemberNumberAndFishIdAndCompetitionCode(Integer memberNumber, Long fishId, String competitionCode) {
        return huntingRepository.existsByMember_NumAndFish_IdAndCompetition_Code(memberNumber, fishId,competitionCode);
    }

    @Override
    public Hunting findHuntingByMemberNumberAndFishIdAndCompetitionCode(Integer memberNumber, Long fishId, String competitionCode) {
        return  huntingRepository.findByMember_NumAndFish_IdAndCompetition_Code(memberNumber, fishId,competitionCode).orElseThrow(() -> new ResourceNotFoundException("Hunting not found"));
    }


}
