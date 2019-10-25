package pl.sda.employee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.employee.model.Employee;
import pl.sda.employee.model.Sex;
import pl.sda.employee.service.EmployeeService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/")
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

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id).get();
        model.addAttribute("employee", employee);
        if (employee.getSex() == Sex.WOMAN) {
            return "edit-woman";
        } else {
            return "edit-men";
        }
    }

    @PostMapping("edit/{id}")
    public String updateEmployee(Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "result";
        }
        employeeService.createEmployee(employee);
        log.info("created new employee " + employee);
        return "redirect:/";
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
        log.info("add form " + employee);
        return "redirect:/";
    }

    @GetMapping("/sort")
    public String sortAllbyDate(Model model) {
        model.addAttribute("employees", employeeService.sortByDate());
        return "employee";
    }

    @GetMapping("/archive/{id}")
    public String addToArchive(@PathVariable Long id) {
        employeeService.addEmployeeToArchive(id);
        return "redirect:/";
    }

    @GetMapping("/show-archive")
    public String showArchive(Model model){
        model.addAttribute("employees", employeeService.showArchive());
        return "archive";
    }

    @GetMapping("/back")
    public String backToMainPage(Model model){
        showAllEmployees(model);
        return "redirect:/";
    }








}
