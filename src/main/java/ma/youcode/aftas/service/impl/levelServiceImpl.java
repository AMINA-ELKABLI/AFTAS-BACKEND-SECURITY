package ma.youcode.aftas.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.exception.ResourceAlreadyExistsException;
import ma.youcode.aftas.exception.ResourceNotFoundException;
import ma.youcode.aftas.model.Level;
import ma.youcode.aftas.repository.LevelRepository;
import ma.youcode.aftas.service.IlevelService;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class levelServiceImpl implements IlevelService {
    private final LevelRepository levelRepository;


    @Override
    public Level create(Level level) {
        canAddLevel(level);
        return levelRepository.save(level);
    }

    @Override
    public void deleteLevelById(Long id) {
        if (existsById(id)) {
            levelRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Level does not exist");
        }

    }

    @Override
    public Level findLevelById(Long id) {
        return levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + id));
    }

    @Override
    public Boolean existsById(Long id) {
        return levelRepository.existsById(id);
    }

    @Override
    public Page<Level> findAll(Pageable pageable) {
        return levelRepository.findAll(pageable);
    }

    @Override
    public Long countLevels() {
        return levelRepository.count();
    }

    @Override
    public Level getLevelByCode(Integer code) {
        return levelRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Level not found"));
    }

    private void canAddLevel(Level level) {
        if (levelRepository.findByCode(level.getCode()).isPresent()) {
            throw new ResourceAlreadyExistsException("Level already exists with this code: " + level.getCode());
        }
        List<Level> existingLevels = levelRepository.findAll();
        if (existingLevels.stream().allMatch(l -> l.getPoints() < level.getPoints())) {
        } else {
            throw new IllegalArgumentException(" new level cannot be created with points less than or equal to an existing level");
        }
    }
}