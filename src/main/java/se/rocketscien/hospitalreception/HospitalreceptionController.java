package se.rocketscien.hospitalreception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;
import se.rocketscien.hospitalreception.pojo.PatientRepository;
import se.rocketscien.hospitalreception.PatientService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class HospitalreceptionController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable("patientId") Long patientId) {
        Patient patient = patientService.getPatient(patientId);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody @Valid Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patient));
    }

    @PutMapping(value = "/{patientId}")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient,
                                                 @PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(patientService.updatePatient(patient, patientId));
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(patientService.deletePatient(patientId));
    }
}
