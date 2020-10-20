package se.rocketscien.hospitalreception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class HospitalreceptionController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patients = (List<Patient>) patientRepository.findAll();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable("patientId") Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) {
            throw new EntityNotFoundException("id: " + patientId);
        }
        return ResponseEntity.ok().body(patient.get());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody @Valid Patient patient) {
        Patient patientReturned = patientRepository.save(patient);
        return ResponseEntity.status(201).body(patientReturned);
    }

    @PutMapping(value = "/{patientId}")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient,
                                                 @PathVariable("patientId") Long patientId) {
        Optional<Patient> patient1 = patientRepository.findById(patientId);
        Patient oldPerson = patient1.get();
        oldPerson.setLastName(patient.getLastName());
        oldPerson.setMiddleName(patient.getMiddleName());
        oldPerson.setFirstName(patient.getFirstName());
        return ResponseEntity.ok().body(patientRepository.save(oldPerson));
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("patientId") Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        patientRepository.deleteById(patientId);
        return ResponseEntity.ok().body(patient.get());
    }

//    @PostMapping()
//    private Patient searchPatient(Patient patient) {
//        return patientRepository.searchByLastNameAndMiddleNameAndFirstName(patient.getLastName(), patient.getMiddleName(), patient.getFirstName());
//    }
}