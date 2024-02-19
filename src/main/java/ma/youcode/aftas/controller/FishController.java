package ma.youcode.aftas.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.dto.CreateUpdateFishDto;
import ma.youcode.aftas.dto.response.FishResponseDto;
import ma.youcode.aftas.model.Fish;
import ma.youcode.aftas.service.IFishService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fish")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FishController {
    private final IFishService fishService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<FishResponseDto> createFish(@RequestBody CreateUpdateFishDto createFishDto) {
        Fish createdFish = fishService.save(modelMapper.map(createFishDto, Fish.class));
        FishResponseDto responseDto = modelMapper.map(createdFish, FishResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<FishResponseDto> getFishByName(@PathVariable String name) {
        return fishService.findByName(name)
                .map(fish -> ResponseEntity.ok(modelMapper.map(fish, FishResponseDto.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity getAllFish(Pageable pageable) {
        return ResponseEntity.ok(fishService.findAll(pageable)
                .stream()
                .map(fish -> modelMapper.map(fish, FishResponseDto.class)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Long id) {
        fishService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity countFishes(){
        Long count = fishService.countFishes();
        return ResponseEntity.ok(count);
    }
}
