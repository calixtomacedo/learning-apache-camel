package br.com.cmdev.address.repository;

import br.com.cmdev.address.domain.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends ListCrudRepository<Address, Long> {
}
