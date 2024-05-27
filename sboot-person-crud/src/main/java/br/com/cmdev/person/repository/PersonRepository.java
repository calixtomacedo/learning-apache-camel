package br.com.cmdev.person.repository;

import br.com.cmdev.person.domain.Person;
import org.springframework.data.repository.ListCrudRepository;


public interface PersonRepository extends ListCrudRepository<Person, Long> {

}
