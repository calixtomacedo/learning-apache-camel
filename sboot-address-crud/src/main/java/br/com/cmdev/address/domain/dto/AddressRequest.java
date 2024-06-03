package br.com.cmdev.address.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AddressRequest(

        Long idAddress,

        @NotNull
        Long idPerson,

        @NotBlank @Size(min = 3, max = 100)
        String street,

        String number,

        @NotBlank @Size(min = 3, max = 100)
        String neighborhood,

        @NotBlank @Size(min = 3, max = 100)
        String city,

        @NotBlank @Size(min = 2, max = 100)
        String state,

        @NotBlank @Size(min = 6, max = 10)
        String zipcode,

        Boolean isActive

) {
}
