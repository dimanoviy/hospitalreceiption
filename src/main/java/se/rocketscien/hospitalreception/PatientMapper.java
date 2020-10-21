package se.rocketscien.hospitalreception;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDto patientToPatientDto(Patient patient);
}
