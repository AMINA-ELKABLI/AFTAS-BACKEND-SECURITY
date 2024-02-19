package ma.youcode.aftas.repository;

import ma.youcode.aftas.model.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
     Optional<Competition> findByCode(String code);
     Page<Competition> findAllByCodeContainingOrLocationContainingAndDateEquals(String search1, String search2, LocalDate date, Pageable pageable);
     Page<Competition> findAllByCodeContainingOrLocationContaining(String search1,String search2, Pageable pageable);
     Page<Competition> findAllByDateEquals(LocalDate date, Pageable pageable);
     Page<Competition> findAllByCodeEquals(String code, Pageable pageable);

}
