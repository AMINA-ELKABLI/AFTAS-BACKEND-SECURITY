package ma.youcode.aftas.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.dto.CreateUpdateHuntingDto;
import ma.youcode.aftas.dto.CreateUpdateMemberDto;
import ma.youcode.aftas.dto.CreateUpdateRankingDto;
import ma.youcode.aftas.dto.response.FishResponseDto;
import ma.youcode.aftas.dto.response.HuntingResponseDto;
import ma.youcode.aftas.dto.response.MemberResponseDto;
import ma.youcode.aftas.dto.response.RankingResponseDto;
import ma.youcode.aftas.model.Fish;
import ma.youcode.aftas.model.Hunting;
import ma.youcode.aftas.model.Member;
import ma.youcode.aftas.model.Ranking;
import ma.youcode.aftas.service.IFishService;
import ma.youcode.aftas.service.IHuntingService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hunting")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HuntingController {

    private final IHuntingService huntingService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<HuntingResponseDto> createHunting(@Validated @RequestBody CreateUpdateHuntingDto createHuntingDto) {
        Hunting createdHunting = huntingService.createHunting(createHuntingDto);
        HuntingResponseDto responseDto = modelMapper.map(createdHunting, HuntingResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


}
