package br.com.cmdev.person.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonDTO(
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotNull
        LocalDate birthDate,

        @NotNull @Min(1) @Max(100)
        Integer age
) {
}
