package ma.youcode.aftas.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateCompetitionDto {
    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date must be in the present or future")
    LocalDate date;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time should be in the present or future")
    LocalTime  startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time should be in the present or future")
    LocalTime  endTime;

    @NotNull(message = "Number of participants is required")
    @Min(value = 1, message = "Number of participants should be at least 1")
    Integer numberOfParticipants;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location should not exceed 255 characters")
    String location;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount should be positive")
    Double amount;
    @AssertTrue(message = "End time should be greater than Start time")
    public boolean isEndTimeAfterStartTime() {
        return endTime.isAfter(startTime);


    }
}


