package br.com.cmdev.atom.users.registration.domain;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRequest(

        @NotBlank(message = "O campo nome é obrigatório")
        @Size(min = 3, max = 100, message = "O campo nome deve conter entre 3 e 100 caracteres")
        String name,

        @NotBlank(message = "O campo email é obrigatório")
        @Size(min = 8, max = 100, message = "O campo email deve conter entre 8 e 100 caracteres")
        @Email(message = "O campo email deve conter um email válido")
        String email,

        @NotBlank(message = "O campo senha é obrigatório")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,250}$", message = "A senha deve ter entre 8 e 250 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial")
        String password,

        @NotNull(message = "O campo data de nascimento é obrigatório")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dateBirth
) {
}
