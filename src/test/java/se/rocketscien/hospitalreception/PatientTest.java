package se.rocketscien.hospitalreception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import se.rocketscien.hospitalreception.pojo.PatientDto;
import se.rocketscien.hospitalreception.pojo.PatientRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class PatientTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void createPatientTest() {
        String lastName = "Jordan";
        String firstName = "Michael";
        long id = 1001;
        PatientDto patient = PatientDto.builder().lastName(lastName).firstName(firstName).patientId(id).build();
        System.out.println(objectMapper.writeValueAsString(patient));
        ResponseEntity<PatientDto> response = testRestTemplate.postForEntity("http://localhost:8080/patients",
                patient, PatientDto.class);
//        assertThat(response.getBody().getLastName()).isEqualTo(lastName);
//        assertThat(response.getBody().getFirstName()).isEqualTo(firstName);
//        assertThat(response.getBody().getPatientId()).isNotNull();
    }

    @Test
    void readPatient() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/patients", HttpMethod.GET,
                null, new ParameterizedTypeReference<String>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<PatientDto> patients = objectMapper.readValue(response.getBody(), new TypeReference<List<PatientDto>>(){});
        assertThat(patients).hasSizeGreaterThan(0);
    }

    @Test
    void updatePatientTest() {
        long id = 16;
        PatientDto patientBad = PatientDto.builder().firstName("Man").middleName("Bad").lastName("Very").patientId(id).build();
        testRestTemplate.put("/patients/{id}", patientBad, id);
        assertThat(testRestTemplate.getForObject("/patients/{id}", PatientDto.class, id)).isEqualTo(patientBad);
    }

    @Test
    void deletePatientTest() {
        long id = 1001;
        PatientDto patient = PatientDto.builder().lastName("Sorry").middleName("Youllbe").firstName("Lost").patientId(id).build();
        testRestTemplate.delete("/patients/{id}", id);
        assertThat(patientRepository.findById(id)).isEqualTo(Optional.empty());
    }
}
