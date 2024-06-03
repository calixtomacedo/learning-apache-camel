package br.com.cmdev.address.domain.dto;

import java.time.LocalDateTime;

public record AddressResponse(Long idAdresses, Long idPerson, String street, String number, String neighborhood, String city, String state, String zipcode, Boolean isActive, String creationDate, String changeDate) {}
