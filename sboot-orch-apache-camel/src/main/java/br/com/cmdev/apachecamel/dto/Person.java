package br.com.cmdev.apachecamel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long idPerson;
    private String name;
    private String email;
    private String birthDate;
    private Integer age;
    private String isActive;
    private String creationDate;
    private String changeDate;

    private List<Address> address;
}