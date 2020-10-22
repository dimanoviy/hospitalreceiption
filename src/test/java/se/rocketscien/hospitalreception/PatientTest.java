package se.rocketscien.hospitalreception;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.rocketscien.hospitalreception.pojo.PatientDto;
import se.rocketscien.hospitalreception.pojo.PatientRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void createPatientTest() {
        String lastName = "Jordan";
        String firstName = "Michael";
        PatientDto patient = PatientDto.builder().lastName(lastName).firstName(firstName).build();
        ResponseEntity<PatientDto> response = testRestTemplate.postForEntity("http://localhost:8080/patients",
                patient, PatientDto.class);
        assertThat(response.getBody().getLastName()).isEqualTo(lastName);
        assertThat(response.getBody().getFirstName()).isEqualTo(firstName);
        assertThat(response.getBody().getPatientId()).isNotNull();
    }

    @Test
    void readPatient() {
        createTestPatient("Lastovikh", "Middlovich", "Nam");
        ResponseEntity<List<PatientDto>> response = testRestTemplate.exchange("/patients", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PatientDto>>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<PatientDto> patients = response.getBody();
        assertThat(patients).hasSizeGreaterThan(0);
    }

    @Test
    void updatePatientTest() {
        PatientDto patient = new PatientDto("Very", "Good", "Man");
        PatientDto patientBad = new PatientDto("Very", "Bad", "Man");
        long id = createTestPatient(patient).getPatientId();
        testRestTemplate.put("/patients/{id}", patientBad, id);
        assertThat(testRestTemplate.getForObject("/patients/{id}", PatientDto.class, id)).isEqualTo(patientBad);
    }

    @Test
    void deletePatientTest() {
        PatientDto patient = new PatientDto("Sorry", "youllbe", "Lost");
        long id = createTestPatient(patient).getPatientId();
        testRestTemplate.delete("/patients/{id}", id);
        assertThat(patientRepository.findById(id)).isEqualTo(Optional.empty());
    }

    private PatientDto createTestPatient(String lastName, String middleName, String firstName) {
        PatientDto patientDto = PatientDto.builder().lastName(lastName).middleName(middleName).firstName(firstName).build();
        return patientRepository.save((patientDto));
    }
}
