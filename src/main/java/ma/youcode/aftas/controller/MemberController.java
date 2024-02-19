package ma.youcode.aftas.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.dto.CreateUpdateMemberDto;
import ma.youcode.aftas.dto.response.MemberResponseDto;
import ma.youcode.aftas.model.Member;
import ma.youcode.aftas.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final IMemberService memberService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@Validated @RequestBody CreateUpdateMemberDto createMemberDto) {
        Member createdMember = memberService.save(modelMapper.map(createMemberDto, Member.class));
        MemberResponseDto responseDto = modelMapper.map(createdMember, MemberResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{num}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Integer num, @Validated @RequestBody CreateUpdateMemberDto updateMemberDto) {
        Member updatedMember = memberService.update(modelMapper.map(updateMemberDto, Member.class), num);
        MemberResponseDto responseDto = modelMapper.map(updatedMember, MemberResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{num}")
    public ResponseEntity<MemberResponseDto> getMemberByNum(@PathVariable Integer num) {
        return memberService.findByNum(num)
                .map(member -> ResponseEntity.ok(modelMapper.map(member, MemberResponseDto.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers(Pageable pageable) {
        return ResponseEntity.ok(memberService.findAll(pageable)
                .stream()
                .map(member -> modelMapper.map(member, MemberResponseDto.class)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer num) {
        if (memberService.delete(num)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/count")
    public ResponseEntity countMembers() {
        Long count = memberService.countMembers();
        return ResponseEntity.ok(count);
    }
}
