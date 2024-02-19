package ma.youcode.aftas.service;

import ma.youcode.aftas.dto.CreateUpdateRankingDto;
import ma.youcode.aftas.model.Ranking;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRankingService {

    Ranking save(CreateUpdateRankingDto createUpdateRankingDto);

    List<Ranking> getAllRankings(Pageable pageable);
    Ranking updateRanking(Ranking ranking);
    Ranking findRankingByMemberNumberAndCompetitionCode(Integer number, String competitionCode);
    List<Ranking> updateAllRankings(Ranking ranking);

}
