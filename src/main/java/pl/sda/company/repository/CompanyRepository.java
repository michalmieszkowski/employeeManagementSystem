package pl.sda.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.company.model.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository <Company, Long>   {

    Company save(Company company);

    List<Company> findAll();

    Optional<Company> findById(Long id);


}
