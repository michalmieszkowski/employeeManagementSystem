package pl.sda.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.employee.Employee;

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
    private CompanyAddress address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Employee> employeeList;

    private String name;

}
