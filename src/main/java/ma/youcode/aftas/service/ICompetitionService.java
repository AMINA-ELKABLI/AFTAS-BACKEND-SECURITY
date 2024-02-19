package ma.youcode.aftas.service;

import ma.youcode.aftas.model.Competition;
import ma.youcode.aftas.model.Fish;
import ma.youcode.aftas.model.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ICompetitionService {
    Competition save(Competition competition);
    Page<Competition> findAll(Pageable pageable);
    Optional<Competition> findByCode(String code);
    Optional<Competition> findById(Long id);
    Boolean existsById(String id);
    Long countCompetitions();
    List<Competition> getAllCompetitions(Pageable pageable, LocalDate date);



}
