package br.com.cmdev.address.controller;

import br.com.cmdev.address.domain.dto.AddressRequest;
import br.com.cmdev.address.domain.dto.AddressResponse;
import br.com.cmdev.address.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cmdev/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody AddressRequest request) {
        this.service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<AddressResponse> response = this.service.findAll();
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        AddressResponse response = this.service.findById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity findByPersonId(@PathVariable("id") Long personId) {
        List<AddressResponse> response = this.service.findByIdPerson(personId);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/inactive")
    public ResponseEntity findInactive() {
        List<AddressResponse> response = this.service.findInactive();
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody AddressRequest request) {
        this.service.update(request);
        return this.findById(request.idAddress());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return this.findAll();
    }

}
