package ma.youcode.aftas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateUpdateHuntingDto {


    @NotNull(message = "Competition ID is required")
    private String competitionCode;

    @NotNull(message = "Fish ID is required")
    private Long fishId;

    @NotNull(message = "Member ID is required")
    private Integer memberNum;
    @NotNull(message = "Weight is required")
    private Double weight;

}
