package se.rocketscien.hospitalreception.pojo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
        Patient patient = new Patient(lastName, "A.", firstName);
        ResponseEntity<Patient> response = testRestTemplate.postForEntity("http://localhost:8080/patients",
                patient, Patient.class);
        assertThat(response.getBody().getLastName(), is(lastName));
        assertThat(response.getBody().getFirstName(), is(firstName));
        assertThat(response.getBody().getPatientId(), notNullValue());
    }

    @Test
    void readPatient() {
        createTestPatient("Lastovikh", "Middlovich", "Nam");
        ResponseEntity<List<Patient>> response = testRestTemplate.exchange("/patients", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Patient>>() {
                });
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        List<Patient> patients = response.getBody();
        assertThat(patients, hasSize(not(0)));
    }

    @Test
    void updatePatientTest() {
        Patient patient = new Patient("Very", "Good", "Man");
        Patient patientBad = new Patient("Very", "Bad", "Man");
        long id = createTestPatient(patient).getPatientId();
        testRestTemplate.put("/patients/{id}", patientBad, id);
        assertThat(testRestTemplate.getForObject("/patients/{id}", Patient.class, id), is(patientBad));
    }

    @Test
    void deletePatientTest() {
        Patient patient = new Patient("Sorry", "youllbe", "Lost");
        long id = createTestPatient(patient).getPatientId();
        testRestTemplate.delete("/patients/{id}", id);
        assertThat(patientRepository.findById(id), is(Optional.empty()));
    }

    private Patient createTestPatient(String lastName, String middleName, String firstName) {
        Patient patient = new Patient(lastName, middleName, firstName);
        return patientRepository.save(patient);
    }

    private Patient createTestPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}