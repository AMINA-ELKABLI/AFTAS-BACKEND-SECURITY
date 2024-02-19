package ma.youcode.aftas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
public class Hunting  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Fish fish;
    @ManyToOne
    private Member member;

}
