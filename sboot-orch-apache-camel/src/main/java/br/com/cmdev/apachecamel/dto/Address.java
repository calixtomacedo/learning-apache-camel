package br.com.cmdev.apachecamel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long idAddress;
    private Long idPerson;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipcode;
    private String isActive;
    private String creationDate;
    private String changeDate;
}