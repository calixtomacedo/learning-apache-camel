package br.com.cmdev.person.domain;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    public static Person requestToPerson(PersonRequest request) {
        return new Person(request.name(), request.email(), request.birthDate(), request.age(), request.isActive());
    }

    public static List<PersonResponse> personToResponse(List<Person> personList) {
        List<PersonResponse> responseList = new ArrayList<>();
        personList.forEach(person -> {
            var response = new PersonResponse(
                    person.getIdPerson(),
                    person.getName(),
                    person.getEmail(),
                    person.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    person.getAge(), person.getIsActive(),
                    person.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                    person.getChangeDate() != null ? person.getChangeDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null
            );
            responseList.add(response);
        });
        return responseList;
    }
}
