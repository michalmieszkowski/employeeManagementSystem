package pl.sda.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull
    private long pesel;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private Sex sex;

    private Float salary;

    private Date start;

    private Boolean criminalRecord = false;

    private Boolean archive = false;

}
