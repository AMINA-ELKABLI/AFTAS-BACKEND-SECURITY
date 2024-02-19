package ma.youcode.aftas.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.exception.InvalidDataException;
import ma.youcode.aftas.exception.ResourceNotFoundException;
import ma.youcode.aftas.model.Competition;
import ma.youcode.aftas.repository.CompetitionRepository;
import ma.youcode.aftas.service.ICompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CompetitionServiceImpl implements ICompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public Competition save(Competition competition) {
        competition.setStartTime(LocalTime.of(8,00,0));
        competition.setEndTime(LocalTime.of(18,00,0));

        if(competition.getDate().isBefore(LocalDate.now())){
            throw new InvalidDataException("Competition date cannot be in the past");
        }
        List<Competition> competitions = competitionRepository.findAllByDateEquals(competition.getDate(), Pageable.unpaged()).getContent();
        if(competitions.size()>0){
            throw new InvalidDataException("Competition date cannot have more than 1 competition");
        }
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            throw new InvalidDataException("Competition start time cannot be greater than end time");
        }

        String locationCode = competition.getLocation().substring(0, 3).toLowerCase();
        String yearCode = String.valueOf(competition.getDate().getYear()).substring(2);
        String monthCode = String.valueOf(competition.getDate().getMonthValue());
        String dayCode = String.valueOf(competition.getDate().getDayOfMonth());
        String code = locationCode + "-" + yearCode +"-" + monthCode +"-" + dayCode;
        competition.setCode(code);
        return competitionRepository.save(competition);



    }

    @Override
    public Page<Competition> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }


    @Override
    public Optional<Competition> findByCode(String code) {
        return competitionRepository.findByCode(code);
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Boolean existsById(String id) {
        return null;
    }

    @Override
    public Long countCompetitions() {
        return competitionRepository.count();
    }

    @Override
    public List<Competition> getAllCompetitions(Pageable pageable, LocalDate date) {
        if(date != null){

            return competitionRepository.findAllByDateEquals(date,pageable).getContent();
        }
        return competitionRepository.findAll(pageable).getContent();
    }


}
