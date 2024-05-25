package br.com.cmdev.person.controllers;

import br.com.cmdev.person.domain.Person;
import br.com.cmdev.person.domain.PersonDTO;
import br.com.cmdev.person.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody PersonDTO person) {
        this.service.addPerson(new Person(service.getPersonLastId() +1, person.name(), person.email(), person.birthDate(), person.age()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getAllPerson() {
        return ResponseEntity.ok(this.service.getAllPerson());
    }

}
