package se.rocketscien.hospitalreception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.rocketscien.hospitalreception.pojo.Patient;
import se.rocketscien.hospitalreception.pojo.PatientDto;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class HospitalreceptionController {
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @GetMapping
    public ResponseEntity<List<Patient>> listAllPatients() {
//        List<Patient> patients = (List<Patient>) patientRepository.findAll();
        List<Patient> patients = patientService.findAll();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping(value = "/patients/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable("patientId") Long patientId) {
        PatientDto patient = patientMapper.patientToPatientDto(patientService.getPatient(patientId));
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientMapper.patientDtoToPatient(patientDto)));
    }

    @PutMapping(value = "/patients/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto,
                                                 @PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(patientMapper.patientToPatientDto(patientService.updatePatient(patientMapper.patientDtoToPatient(patientDto), patientId)));
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok().body(patientMapper.patientToPatientDto(patientService.deletePatient(patientId)));
    }
}
