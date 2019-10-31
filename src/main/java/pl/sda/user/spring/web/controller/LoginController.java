package pl.sda.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class LoginController {

    @PostMapping("/login")
    public String addEmployeeToCompany(HttpServletRequest request){
        System.out.println("i am here !");
        return "employee";
    }


}
