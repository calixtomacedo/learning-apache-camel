package br.com.cmdev.person.services;

import br.com.cmdev.person.domain.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> personList = new ArrayList<>();

    @PostConstruct
    private void init(){
        Person calixto = new Person(1L, "Calixto Macedo", "calixto.macedo@gmail.com", LocalDate.of(1972, Month.FEBRUARY, 12), 52);
        personList.add(calixto);
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public List<Person> getAllPerson() {
        return this.personList;
    }

    public Long getPersonLastId(){
        return this.personList.getLast().getId();
    }
}
