package ir.maktab.service.mapper;

import ir.maktab.data.domains.Address;
import ir.maktab.dto.AddressDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class AddressMapperImpl implements AddressMapper {
    @Override
    public Address toAddress(AddressDto addressDto) {
        return new Address().setCity(addressDto.getCity())
                .setStreet(addressDto.getStreet())
                .setAlley(addressDto.getAlley());
    }

    @Override
    public AddressDto toAddressDto(Address address) {
        return new AddressDto().setCity(address.getCity())
                .setStreet(address.getStreet())
                .setAlley(address.getAlley());
    }
}
