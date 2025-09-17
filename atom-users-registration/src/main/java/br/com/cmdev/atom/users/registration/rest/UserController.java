package br.com.cmdev.atom.users.registration.rest;

import br.com.cmdev.atom.users.registration.domain.UserRequest;
import br.com.cmdev.atom.users.registration.entity.User;
import br.com.cmdev.atom.users.registration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody UserRequest request, UriComponentsBuilder uriBuilder) {
        var userCreated = this.service.save(request);
        //var uri = uriBuilder.path("/user/{id}").buildAndExpand(userCreated.getUserId()).toUri();
        //return ResponseEntity.created(uri).body(userCreated);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listAll() {
        var users = service.listAllUser();
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

}
