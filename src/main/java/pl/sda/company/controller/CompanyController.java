package pl.sda.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.company.Company;
import pl.sda.company.CompanyAddress;
import pl.sda.company.service.CompanyService;
import pl.sda.employee.Employee;
import pl.sda.employee.service.EmployeeService;


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
        model.addAttribute("companyAddress", new CompanyAddress());
        return "company";
    }

    @PostMapping("/add-company")
    public String createNewCompany(Company company, BindingResult result){
        if(result.hasErrors()){
            return "company";
        }
        companyService.createNewCompany(company);
        return "redirect:/employee";
    }

    @GetMapping("/companies-list/employee/{employeeId}")
    public String showCompanyList(@PathVariable Long employeeId, Model model){
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("companies", companyService.showAllCompanies());
        model.addAttribute("employee", employee);
        return "companies-list";
    }

    @GetMapping("companies-list/employee/{employeeId}/company/{companyId}")
    public String addEmployeeToCompanyForm(@PathVariable Long employeeId, @PathVariable Long companyId, Model model){
        model.addAttribute("companies", companyId);
        model.addAttribute("employee", employeeId);
        return "companies-list";
    }

    @GetMapping("companies-list/employee/{employeeId}/company/{companyId}/submit")
    public String addEmployeeToCompanySubmit(@PathVariable Long employeeId, @PathVariable Long companyId){
        Company company = companyService.findCompanyById(companyId);
        Employee employee = employeeService.findById(employeeId);
        employee.setCompany(company);
        companyService.addEmployeeToCompany(company, employee);
        return "redirect:/employee";
    }
}
