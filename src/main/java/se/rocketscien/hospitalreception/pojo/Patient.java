package se.rocketscien.hospitalreception.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;
    private String lastName;
    private String middleName;
    private String firstName;
    private LocalDate birthDate;
}
