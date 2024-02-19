package ma.youcode.aftas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LevelResponseDto {
     long id;
     Integer code;
     String description;
     Integer points;


}