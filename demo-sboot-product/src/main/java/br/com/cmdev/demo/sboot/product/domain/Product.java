package br.com.cmdev.demo.sboot.product.domain;

import java.math.BigDecimal;

public record Product(Integer id, String name, String description, BigDecimal price) {}
