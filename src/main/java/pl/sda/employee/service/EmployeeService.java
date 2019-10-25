package pl.sda.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.employee.model.Employee;
import pl.sda.employee.repository.EmployeesRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    EmployeesRepository employeesRepository;

    @Autowired
    public EmployeeService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public List<Employee> showAllEmployees() {
        return employeesRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }

    public Optional<Employee> findById(Long id) {
        return employeesRepository.findById(id);
    }

    public List<Employee> sortByDate() {
        List<Employee> sorted = employeesRepository.findAll().stream()
                .sorted(Comparator.comparing(employee -> employee.getStart()))
                .collect(Collectors.toList());
        return sorted;
    }
}
