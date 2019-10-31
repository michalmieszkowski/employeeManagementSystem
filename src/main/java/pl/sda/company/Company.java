package pl.sda.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.employee.model.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private CompanyAdress adress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Employee> employeeList;

    private String name;




}
