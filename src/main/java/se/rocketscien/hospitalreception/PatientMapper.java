package se.rocketscien.hospitalreception;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto patientToPatientDto(Patient patient);

    @InheritInverseConfiguration
    default Patient patientDtoToPatient(PatientDto patientDto) {
        if (patientDto == null) {
            return null;
        }

        Long patientId = null;
        String lastName = null;
        String middleName = null;
        String firstName = null;
        LocalDate birthDate = null;

        patientId = patientDto.getPatientId();
        lastName = patientDto.getLastName();
        middleName = patientDto.getMiddleName();
        firstName = patientDto.getFirstName();
        birthDate = patientDto.getBirthDate();

        Patient patient = new Patient(patientId, lastName, middleName, firstName, birthDate);

        return patient;
    }

    ;
}
