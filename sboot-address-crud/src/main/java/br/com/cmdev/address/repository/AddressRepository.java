package br.com.cmdev.address.repository;

import br.com.cmdev.address.domain.Address;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends ListCrudRepository<Address, Long> {

    List<Address> findByIsActiveTrue();

    List<Address> findByIsActiveFalse();

    Optional<Address> findByIdAddressAndIsActiveTrue(Long id);

}
