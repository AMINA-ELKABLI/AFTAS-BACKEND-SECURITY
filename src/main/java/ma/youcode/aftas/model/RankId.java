package ma.youcode.aftas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class RankId implements Serializable {
    @Column(name = "member_id")
    private Integer memberNumber;

    @Column(name = "competition_id")
    private String competitionCode;
}
