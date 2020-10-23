package se.rocketscien.hospitalreception.pojo;

import lombok.Builder;
import lombok.Value;

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
