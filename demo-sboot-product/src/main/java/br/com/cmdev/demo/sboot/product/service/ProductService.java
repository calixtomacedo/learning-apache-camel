package br.com.cmdev.demo.sboot.product.service;

import br.com.cmdev.demo.sboot.product.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>();

    public ProductService() {
        create();
    }

    public void create() {
        var bike = new Product(1, "BIKE", "TARMAC PRO 2026", new BigDecimal(100000.00).setScale(2));
        var notebook = new Product(2, "NOTEBOOK", "DELL PRO 2026", new BigDecimal(10000.00).setScale(2));
        var phone = new Product(3, "CELL PHONE", "XIAOMI PRO 2026", new BigDecimal(5000.00).setScale(2));
        products.add(bike);
        products.add(notebook);
        products.add(phone);
    }

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Integer id) {
        return products.stream().filter(product -> product.id().equals(id)).findFirst().orElse(null);
    }

}
