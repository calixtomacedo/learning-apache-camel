package br.com.cmdev.address.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_adresses")
public class Address {

    private Long idAdresses;
    private Long idPerson;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private State state;
    private String zipcode;
    private Boolean isActive;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

    public Address(Long idPerson, String street, String number, String neighborhood, String city, State state, String zipcode) {
        this.idPerson = idPerson;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.isActive = isActive == null ? Boolean.TRUE : isActive;
        this.creationDate = LocalDateTime.now();
        this.changeDate = this.idPerson != null ? LocalDateTime.now() : null;
    }

}
