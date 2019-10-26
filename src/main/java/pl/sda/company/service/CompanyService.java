package pl.sda.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.company.model.Company;
import pl.sda.company.repository.CompanyRepository;
import pl.sda.employee.model.Employee;
import pl.sda.employee.repository.EmployeesRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeesRepository employeesRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, EmployeesRepository employeesRepository) {
        this.companyRepository = companyRepository;
        this.employeesRepository = employeesRepository;
    }

    public Company createNewCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> showAllCompanies(){
        return companyRepository.findAll();
    }

    public void addEmployeeToCompany(Company company, Employee employee){
        company.getEmployeeList().add(employee);
        companyRepository.save(company);
        employeesRepository.save(employee);
    }

    public Optional<Company> findCompanyById(Long id){
        return companyRepository.findById(id);
    }






}
