package ma.youcode.aftas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.aftas.model.Competition;
import ma.youcode.aftas.model.Fish;
import ma.youcode.aftas.model.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HuntingResponseDto {
    private Long id;
    private Fish fish;
    private Member member;

}
