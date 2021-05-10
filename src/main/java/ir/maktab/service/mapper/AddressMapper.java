package ir.maktab.service.mapper;

import ir.maktab.data.domains.Address;
import ir.maktab.dto.AddressDto;

public interface AddressMapper {
    Address toAddress(AddressDto addressDto);
    AddressDto toAddressDto(Address address);
}
