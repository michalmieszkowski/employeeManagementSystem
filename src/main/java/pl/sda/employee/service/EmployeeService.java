package pl.sda.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.employee.Employee;
import pl.sda.employee.repository.EmployeesRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeesRepository employeesRepository;

    @Autowired
    public EmployeeService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public List<Employee> showAllEmployees() {
        return employeesRepository.findAll().stream()
                .filter(employee -> employee.getArchive() == false)
                .collect(Collectors.toList());
    }

    public List<Employee> showArchive() {
        return employeesRepository.findAll().stream()
                .filter(Employee::getArchive)
                .collect(Collectors.toList());
    }

    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employeesRepository.findById(id);
        return employee.orElse(null);
    }

    public List<Employee> sortByDate() {
        return showAllEmployees().stream()
                .sorted(Comparator.comparing(employee -> employee.getStart()))
                .collect(Collectors.toList());
    }

    public Employee addEmployeeToArchive(Long id) {
        Optional<Employee> employeeArchive = employeesRepository.findById(id);
        if (employeeArchive.isPresent()) {
            employeeArchive.get().setArchive(true);
            createEmployee(employeeArchive.get());
            return employeeArchive.get();
        } else
            return null;
    }

}

