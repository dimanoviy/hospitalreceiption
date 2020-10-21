package se.rocketscien.hospitalreception.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
public class Patient  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;

    @NotNull
    private String lastName;

    @NotNull
    private String middleName;

    @NotNull
    private String firstName;

//    private Date birthDate;

//    @OneToMany(mappedBy = "patients")
//    private Set<Phone> phoneNumbers;

    protected Patient(){}

    public Patient(@NotNull String lastName, @NotNull String middleName, @NotNull String firstName) {
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
    }

}
