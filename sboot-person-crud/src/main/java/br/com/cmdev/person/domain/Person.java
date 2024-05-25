package br.com.cmdev.person.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer age;

}
