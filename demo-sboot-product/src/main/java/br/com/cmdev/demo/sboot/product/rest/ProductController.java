package br.com.cmdev.demo.sboot.product.rest;

import br.com.cmdev.demo.sboot.product.domain.Product;
import br.com.cmdev.demo.sboot.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        service.save(product);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.id()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listAll() {
        var products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        var product = service.findById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.noContent().build();
    }

}
