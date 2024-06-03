package br.com.cmdev.address.domain.mapper;

import br.com.cmdev.address.domain.Address;
import br.com.cmdev.address.domain.dto.AddressRequest;
import br.com.cmdev.address.domain.dto.AddressResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressMapper {
    public static Address toAddress(AddressRequest request) {
        return new Address(request.idPerson(), request.street(), request.number(), request.neighborhood(), request.city(), request.state(), request.zipcode());

    }

    public static List<AddressResponse> toAddressResponseList(List<Address> addresses) {
        List<AddressResponse> responseList = new ArrayList<>();
        addresses.forEach(address -> {
            var response = new AddressResponse(
                    address.getIdAdresses(),
                    address.getIdPerson(),
                    address.getStreet(),
                    address.getNumber(),
                    address.getNeighborhood(),
                    address.getCity(),
                    address.getState(),
                    address.getZipcode(),
                    address.getIsActive(),
                    address.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                    address.getChangeDate() != null ? address.getChangeDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null
            );
            responseList.add(response);
        });
        return responseList;
    }

    public static AddressResponse toPersonResponse(Optional<Address> addressOptional) {
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            return new AddressResponse(
                    address.getIdAdresses(),
                    address.getIdPerson(),
                    address.getStreet(),
                    address.getNumber(),
                    address.getNeighborhood(),
                    address.getCity(),
                    address.getState(),
                    address.getZipcode(),
                    address.getIsActive(),
                    address.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                    address.getChangeDate() != null ? address.getChangeDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : null
            );
        }
        return null;
    }
}
