package se.rocketscien.hospitalreception.pojo;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient searchByLastNameAndMiddleNameAndFirstName(String lastName, String middleName, String firstName);
}