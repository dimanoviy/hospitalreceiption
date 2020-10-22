package se.rocketscien.hospitalreception.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PatientDto {
    private Long patientId;

    private String lastName;

    private String middleName;

    private String firstName;

    public PatientDto(@NotNull String lastName, @NotNull String middleName, @NotNull String firstName) {
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
    }

    public Long getPatientId() {
        return patientId;
    }
}
