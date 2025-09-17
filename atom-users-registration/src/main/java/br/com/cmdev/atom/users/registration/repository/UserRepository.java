package br.com.cmdev.atom.users.registration.repository;

import br.com.cmdev.atom.users.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
