package se.rocketscien.hospitalreception.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class PatientDto {
    Long patientId;
    String lastName;
    String middleName;
    String firstName;
    LocalDate birthDate;

}
