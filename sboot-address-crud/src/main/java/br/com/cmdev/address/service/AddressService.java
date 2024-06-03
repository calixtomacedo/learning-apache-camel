package br.com.cmdev.address.service;

import br.com.cmdev.address.domain.Address;
import br.com.cmdev.address.domain.dto.AddressRequest;
import br.com.cmdev.address.domain.dto.AddressResponse;
import br.com.cmdev.address.domain.mapper.AddressMapper;
import br.com.cmdev.address.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public void save(AddressRequest request) {
        this.repository.save(AddressMapper.toAddress(request));
    }

    public List<AddressResponse> findAll() {
        List<Address> addresses = this.repository.findAll();
        return AddressMapper.toAddressResponseList(addresses);
    }

    public AddressResponse findById(Long id) {
        Optional<Address> addressOptional = this.repository.findById(id);
        AddressResponse addressResponse = AddressMapper.toPersonResponse(addressOptional);
        return addressResponse;
    }

    public void update(AddressRequest request) {
        var address = this.repository.findById(request.idAddress());
        if (address.isPresent()) {
            address.get().setIdPerson(request.idPerson());
            address.get().setStreet(request.street());
            address.get().setNumber(request.number());
            address.get().setNeighborhood(request.neighborhood());
            address.get().setCity(request.city());
            address.get().setZipcode(request.zipcode());
            address.get().setIsActive(request.isActive() !=  null ? request.isActive() : address.get().getIsActive());
            address.get().setChangeDate(LocalDateTime.now());
            this.repository.save(address.get());
        }
    }

    public void delete(Long id) {
        Optional<Address> address = this.repository.findById(id);
        if (address.isPresent()) {
            this.repository.delete(address.get());
        }
    }
}
