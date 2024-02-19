package ma.youcode.aftas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Competition  {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private  LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition")
    private List<Hunting> hangings;
}