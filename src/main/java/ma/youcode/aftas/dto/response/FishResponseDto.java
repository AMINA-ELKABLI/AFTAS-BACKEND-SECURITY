package ma.youcode.aftas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class FishResponseDto {
    private Long id;
    private int num;
    private String name;
    private double averageWeight;
    private LevelResponseDto level;



}
