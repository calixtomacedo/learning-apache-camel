package br.com.cmdev.person.service;

import br.com.cmdev.person.domain.PersonRequest;
import br.com.cmdev.person.domain.PersonMapper;
import br.com.cmdev.person.domain.PersonResponse;
import br.com.cmdev.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public void save(PersonRequest request) {
        this.repository.save(PersonMapper.requestToPerson(request));
    }


    public List<PersonResponse> getAllPerson() {
        return PersonMapper.personToResponse(this.repository.findAll());
    }

}
