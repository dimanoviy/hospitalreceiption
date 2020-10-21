package se.rocketscien.hospitalreception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patients = (List<Patient>) patientRepository.findAll();
        return ResponseEntity.ok().body(patients);
    }

    Patient getPatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) {
            throw new EntityNotFoundException("id: " + patientId);
        }
        return patient.get();
    }

    Patient createPatient(Patient patient) {
        Patient patientReturned = patientRepository.save(patient);
        return patientReturned;
    }

    Patient updatePatient(Patient patient, Long patientId) {
        Optional<Patient> patient1 = patientRepository.findById(patientId);
        Patient oldPatient = patient1.get();
        oldPatient.setLastName(patient.getLastName());
        oldPatient.setMiddleName(patient.getMiddleName());
        oldPatient.setFirstName(patient.getFirstName());
        return oldPatient;
    }

    Patient deletePatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        patientRepository.deleteById(patientId);
        return patient.get();
    }

    private Patient searchPatient(Patient patient) {
        return patientRepository.searchByLastNameAndMiddleNameAndFirstName(patient.getLastName(), patient.getMiddleName(), patient.getFirstName());
    }
}
