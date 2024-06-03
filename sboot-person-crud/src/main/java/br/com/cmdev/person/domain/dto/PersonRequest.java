package br.com.cmdev.person.domain.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PersonRequest(
        Long idPerson,

        @NotBlank @Size(min = 3, max = 100)
        String name,

        @NotBlank @Email
        String email,

        @NotNull
        LocalDate birthDate,

        @NotNull @Min(1) @Max(100)
        Integer age,

        Boolean isActive
) {
}
