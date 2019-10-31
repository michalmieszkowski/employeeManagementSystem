package pl.sda.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdress {

    private String street;
    private String city;
    private String zipcode;
    private String country;



}
