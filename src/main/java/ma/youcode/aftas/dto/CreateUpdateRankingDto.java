package ma.youcode.aftas.dto;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.aftas.model.Competition;
import ma.youcode.aftas.model.Member;
import org.springframework.stereotype.Component;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public final class CreateUpdateRankingDto {
    private Long id;
    @NotNull(message = "Member cannot be null")
    private  Integer memberNum;

    @NotNull(message = "Competition cannot be null")
    private  String competitionCode;



}
