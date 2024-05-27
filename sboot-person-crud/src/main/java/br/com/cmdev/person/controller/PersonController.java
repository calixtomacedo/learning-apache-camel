package br.com.cmdev.person.controller;

import br.com.cmdev.person.domain.PersonRequest;
import br.com.cmdev.person.service.PersonService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity save(@Valid @RequestBody PersonRequest request) {
        this.service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getAllPerson() {
        var response = this.service.getAllPerson();
        if (response != null && !response.isEmpty()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonById(@PathVariable("id") Long id) {
        var response = this.service.getPersonById(id);
        if (response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return this.getAllPerson();
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody PersonRequest request) {
       this.service.update(request);
       return this.getPersonById(request.idPerson());
    }

}
