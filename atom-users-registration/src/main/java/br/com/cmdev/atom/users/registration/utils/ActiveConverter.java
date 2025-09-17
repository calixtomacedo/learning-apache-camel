package br.com.cmdev.atom.users.registration.utils;

import jakarta.persistence.AttributeConverter;

public class ActiveConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean active) {
        if(active == null) return 'N';
        return active ? 'S' : 'N';
    }

    @Override
    public Boolean convertToEntityAttribute(Character character) {
        if (character == null) return Boolean.FALSE;
        return character == 'S';
    }

}
