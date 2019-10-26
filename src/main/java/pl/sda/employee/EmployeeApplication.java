package pl.sda.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ={"pl.sda.employee", "pl.sda.company"}, exclude = JpaRepositoriesAutoConfiguration.class)
@ComponentScan(basePackages = {"pl.sda.employee", "pl.sda.company"})
@EnableJpaRepositories(basePackages = {"pl.sda.employee", "pl.sda.company"})
@EntityScan("pl.sda")
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
