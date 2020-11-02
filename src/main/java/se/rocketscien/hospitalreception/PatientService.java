package se.rocketscien.hospitalreception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;
import se.rocketscien.hospitalreception.pojo.PatientRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

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


    public Patient updatePatient(Patient patient, Long patientId) {
        log.info("update: Starting update transaction for ID={}: {}", patientId, patient);
        Patient patient1 = updatePatientTransaction(patient, patientId);
        log.info("update: Finishing update transaction for ID={}: {}", patientId, patient1);
        return patient1;
    }

    @Transactional
    public Patient updatePatientTransaction(Patient patient, Long patientId) {
        log.debug("update: Started for ID={}: {}", patientId, patient);
        Optional<Patient> patient1 = patientRepository.findById(patientId);
        log.trace("update: Retrieved from DB for ID={}: {}", patientId, patient1);
        if (patient1.isPresent()){
            Patient oldPatient = patient1.get();
            oldPatient.setLastName(patient.getLastName());
            oldPatient.setMiddleName(patient.getMiddleName());
            oldPatient.setFirstName(patient.getFirstName());
            oldPatient.setBirthDate(patient.getBirthDate());
            log.debug("update: Finished for ID={}: {}", patientId, oldPatient);
            return oldPatient;
        }
        throw new NoSuchElementException();
    }

    Patient deletePatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        patientRepository.deleteById(patientId);
        return patient.get();
    }

    private Patient searchPatient(Patient patient) {
        return patientRepository.searchByLastNameAndMiddleNameAndFirstName(patient.getLastName(), patient.getMiddleName(), patient.getFirstName());
    }

    List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }
}
