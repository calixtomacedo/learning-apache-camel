package br.com.cmdev.atom.users.registration.service;

import br.com.cmdev.atom.users.registration.domain.UserRequest;
import br.com.cmdev.atom.users.registration.entity.User;
import br.com.cmdev.atom.users.registration.mapper.UserMapper;
import br.com.cmdev.atom.users.registration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

   @Autowired
   private UserMapper mapper;

    public User save(UserRequest request) {
        //log.info("Iniciando o processo de gravação do usuário: {}", request.name());
        try {
            //var user = mapper.toEntity(request);
            User user = new User();
            user.setName(request.name());
            user.setEmail(request.email());
            user.setPassword(request.password());
            user.setDateBirth(request.dateBirth());
            var userCreated = repository.save(user);
            //log.info("Finalizando o processo de gravação do usuário: {}", request.name());
            return null;
        } catch (Exception ex) {
            //log.error("Erro ao executar o processo de gravação do usuário: {}. Exception message: {}", request.name(), ex.getMessage());
            throw  ex;
        }
    }

    public List<User> listAllUser() {
        return repository.findAll();
    }

}
