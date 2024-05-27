package br.com.cmdev.person.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_persons")
public class Person implements Serializable {

    @Id
    private Long idPerson;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer age;
    private Boolean isActive;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

    public Person(String name, String email, LocalDate birthDate, Integer age, Boolean isActive) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.age = age;
        this.isActive = isActive == null ? Boolean.TRUE : isActive;
        this.creationDate = LocalDateTime.now();
        this.changeDate = this.idPerson != null ? LocalDateTime.now() : null;
    }
}
