package br.com.cmdev.atom.users.registration.mapper;

import br.com.cmdev.atom.users.registration.domain.UserRequest;
import br.com.cmdev.atom.users.registration.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //@Mapping(target = "userId", ignore = true)
    User toEntity(UserRequest request);

}
