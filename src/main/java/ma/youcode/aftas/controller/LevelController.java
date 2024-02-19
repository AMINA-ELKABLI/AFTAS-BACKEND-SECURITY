package ma.youcode.aftas.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.dto.CreateUpdateLevelDto;
import ma.youcode.aftas.dto.response.LevelResponseDto;
import ma.youcode.aftas.dto.response.MemberResponseDto;
import ma.youcode.aftas.model.Level;
import ma.youcode.aftas.service.IlevelService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/levels")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LevelController {
    private final IlevelService levelService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<LevelResponseDto> createLevel(@RequestBody CreateUpdateLevelDto createLevelDto) {
        Level createdLevel = levelService.create(modelMapper.map(createLevelDto,Level.class));
        LevelResponseDto responseDto = modelMapper.map(createdLevel, LevelResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        levelService.deleteLevelById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelResponseDto> getLevelById(@PathVariable Long id) {
        Level level = levelService.findLevelById(id);
        if (level != null) {
            LevelResponseDto responseDto = modelMapper.map(level, LevelResponseDto.class);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<LevelResponseDto>> getAlllevels(Pageable pageable) {
        return ResponseEntity.ok(levelService.findAll(pageable)
                .map(level-> modelMapper.map(level, LevelResponseDto.class)));
    }
    @GetMapping("/count")
    public ResponseEntity countLevels(){
        Long count = levelService.countLevels();
        return ResponseEntity.ok(count);
    }


}
