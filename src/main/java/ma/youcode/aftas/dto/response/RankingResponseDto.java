package ma.youcode.aftas.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.aftas.model.RankId;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public final class RankingResponseDto {
    private RankId id;
     CompetitionResponseDto competition;
     MemberResponseDto member;
     Integer rank;
     Integer score;




}
