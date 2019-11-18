package pl.sda.employee.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.employee.Employee;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends CrudRepository<Employee, Long> {

    Employee save(Employee employee);

    List<Employee> findAll();

    void deleteById(Long id);

    Optional<Employee> findById(Long id);

}
