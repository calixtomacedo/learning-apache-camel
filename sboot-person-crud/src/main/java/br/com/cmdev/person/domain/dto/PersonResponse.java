package br.com.cmdev.person.domain.dto;

public record PersonResponse(
        Long idPerson,
        String name,
        String email,
        String birthDate,
        Integer age,
        Boolean isActive,
        String creationDate,
        String changeDate
) {
}
