package ma.youcode.aftas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Level  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer code;
    private Double points;
    private String description;
    @OneToMany(mappedBy = "level",fetch = FetchType.LAZY)
    private List<Fish> fishes;


}
