package ma.youcode.aftas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateFishDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Average weight must be a positive value")
    private double averageWeight;

    @NotNull(message = "Level ID cannot be null")
    @Positive(message = "Level ID must be a positive value")
    private Long levelId;
}
