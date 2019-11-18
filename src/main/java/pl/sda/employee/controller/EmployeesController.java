package pl.sda.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.employee.Employee;
import pl.sda.employee.Sex;
import pl.sda.employee.service.EmployeeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeesController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.showAllEmployees());
        return "employee";
    }

    @GetMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{employeeId}")
    public String editEmployee(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        if (employee.getSex() == Sex.WOMAN) {
            return "edit-woman";
        } else {
            return "edit-men";
        }
    }

    @PostMapping("/edit/{employeeId}")
    public String updateEmployee(Employee employee, BindingResult result, @PathVariable Long employeeId) {
        if (result.hasErrors()) {
            return "result";
        }
        employeeService.deleteEmployee(employeeId);
        employeeService.createEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "signup";
    }

    @PostMapping("/signup")
    public String addNewEmployee(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }
        employeeService.createEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/sort")
    public String sortByDate(Model model) {
        model.addAttribute("employees", employeeService.sortByDate());
        return "employee";
    }

    @GetMapping("/archive/{employeeId}")
    public String addEmployeeToArchive(@PathVariable Long employeeId) {
        employeeService.addEmployeeToArchive(employeeId);
        return "redirect:/employee";
    }

    @GetMapping("/show-archive")
    public String showArchive(Model model){
        model.addAttribute("employees", employeeService.showArchive());
        return "archive";
    }

    @GetMapping("/back")
    public String backToMainPage(Model model){
        showAllEmployees(model);
        return "redirect:/employee";
    }








}
