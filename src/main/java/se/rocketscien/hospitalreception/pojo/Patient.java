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

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
