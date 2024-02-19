package ma.youcode.aftas.repository;

import ma.youcode.aftas.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {
    Optional<Ranking> findByMemberAndCompetition(Member member, Competition competition);




    Page<Ranking> findAllByCompetitionCodeOrderByScoreDesc(String competitionCode,Pageable pageable);
    //    get rankings by Member number order by score des

}
