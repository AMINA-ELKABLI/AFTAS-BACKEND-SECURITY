package ma.youcode.aftas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateUpdateLevelDto {
    @NotNull(message = "Code cannot be null")
    private int code;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "Points cannot be null")
    private int points;


}
