package ma.youcode.aftas.service;

import ma.youcode.aftas.model.Level;
import ma.youcode.aftas.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IlevelService {

    Level create(Level level);
    void deleteLevelById(Long id);
    Level findLevelById(Long id);
    Boolean existsById(Long id);
    Page<Level> findAll(Pageable pageable);
    Long countLevels();
    Level getLevelByCode(Integer code);

}
