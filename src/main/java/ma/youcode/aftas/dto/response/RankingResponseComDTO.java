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
public class RankingResponseComDTO {
    private RankId id;
    MemberResponseDto member;
    Integer rank;
    Integer score;
}

