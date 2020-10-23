package se.rocketscien.hospitalreception;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto patientToPatientDto(Patient patient);

    Patient patientDtoToPatient(PatientDto patientDto);
}
