package br.com.cmdev.person.service;

import br.com.cmdev.person.domain.Person;
import br.com.cmdev.person.domain.PersonRequest;
import br.com.cmdev.person.domain.PersonMapper;
import br.com.cmdev.person.domain.PersonResponse;
import br.com.cmdev.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public void save(PersonRequest request) {
        this.repository.save(PersonMapper.toPerson(request));
    }


    public List<PersonResponse> findAll() {
        List<Person> personList = this.repository.findAll();
        return PersonMapper.toPersonResponseList(personList);
    }

    public PersonResponse findById(Long id) {
        Optional<Person> person = this.repository.findById(id);
        PersonResponse personResponse = PersonMapper.toPersonResponse(person);
        return personResponse;
    }

    public void delete(Long id) {
        var person = this.repository.findById(id);
        if (person.isPresent()) {
            this.repository.delete(person.get());
        }
    }

    public void update(PersonRequest request) {
        var person = this.repository.findById(request.idPerson());
        if (person.isPresent()) {
            person.get().setName(request.name());
            person.get().setEmail(request.email());
            person.get().setBirthDate(request.birthDate());
            person.get().setAge(request.age());
            person.get().setIsActive(request.isActive());
            person.get().setChangeDate(LocalDateTime.now());
            this.repository.save(person.get());
        }
    }
}
