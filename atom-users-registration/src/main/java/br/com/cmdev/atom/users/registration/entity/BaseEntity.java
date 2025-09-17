package br.com.cmdev.atom.users.registration.entity;

import br.com.cmdev.atom.users.registration.utils.ActiveConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Convert(converter = ActiveConverter.class)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    private LocalDateTime changeDate;

    @PrePersist
    public void prePersist() {
        this.isActive = Boolean.TRUE;
        this.registrationDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.changeDate = LocalDateTime.now();
    }

}
