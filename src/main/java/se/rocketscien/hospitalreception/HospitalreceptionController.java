package se.rocketscien.hospitalreception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class HospitalreceptionController {
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable("patientId") Long patientId) {
        PatientDto patient = patientMapper.patientToPatientDto(patientService.getPatient(patientId));
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody @Valid PatientDto patientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientMapper.patientDtoToPatient(patientDto)));
    }

    @PutMapping(value = "/{patientId}")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid PatientDto patientDto,
                                                 @PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(patientService.updatePatient(patientMapper.patientDtoToPatient(patientDto), patientId));
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(patientService.deletePatient(patientId));
    }
}
