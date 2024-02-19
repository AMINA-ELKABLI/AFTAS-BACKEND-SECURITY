package ma.youcode.aftas.repository;

import ma.youcode.aftas.model.Competition;
import ma.youcode.aftas.model.Fish;
import ma.youcode.aftas.model.Hunting;
import ma.youcode.aftas.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting,Long> {
    Page<Hunting> findAllByMember_Num(Integer memberNumber, Pageable pageable);
    Page<Hunting> findAllByMember_NumAndCompetition_Code(Integer memberNumber, String competitionCode, Pageable pageable);
    Page<Hunting> findAllByCompetition_Code(String competitionCode, Pageable pageable);

    Optional<Hunting> findByMember_NumAndFish_IdAndCompetition_Code(Integer memberNumber, Long fishId, String competitionCode);
    Boolean existsByMember_NumAndFish_IdAndCompetition_Code(Integer memberNumber, Long fishId, String competitionCode);

}
