package ma.youcode.aftas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Ranking {

    @EmbeddedId
    private RankId id;
    private Integer rank;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberNumber")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionCode")
    private Competition competition;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
