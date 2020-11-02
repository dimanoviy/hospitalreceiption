package se.rocketscien.hospitalreception;

import org.mapstruct.Mapper;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto patientToPatientDto(Patient patient);

    Patient patientDtoToPatient(PatientDto patientDto);
}
