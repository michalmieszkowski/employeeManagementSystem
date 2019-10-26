package pl.sda.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.company.model.Company;
import pl.sda.company.model.CompanyAdress;
import pl.sda.company.service.CompanyService;
import pl.sda.employee.model.Employee;
import pl.sda.employee.service.EmployeeService;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public CompanyController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping("/add-company")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("companyAdress", new CompanyAdress());
        return "company";
    }

    @PostMapping("/add-company")
    public String createNewCompany(Company company, BindingResult result){
        if(result.hasErrors()){
            return "company";
        }
        companyService.createNewCompany(company);
        return "redirect:/";
    }

    @GetMapping("/companiesList/employee/{id}")
    public String showCompanyList(@PathVariable Long id, Model model){
        Optional<Employee> employee = employeeService.findById(id);
        model.addAttribute("companies", companyService.showAllCompanies());
        model.addAttribute("employee", employee);
        return "companyList";
    }

    @GetMapping("companiesList/employee/{id}/company/{id1}")
    public String addEmployeeToCompany(@PathVariable Long id, @PathVariable Long id1, Model model){
        model.addAttribute("companies", id);
        model.addAttribute("employee", id1);
        return "companyList";
    }

    @GetMapping("companiesList/employee/{id}/company/{id1}/submit")
    public String addEmployeeToCompanySubmit(@PathVariable Long id, @PathVariable Long id1){
        Company company = companyService.findCompanyById(id1).get();
        Employee employee = employeeService.findById(id).get();
        employee.setCompany(company);
        companyService.addEmployeeToCompany(company, employee);
        return "redirect:/";
    }
}
