package ir.maktab.dto;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private String emailAddress;

    private String fullName;
    private AddressDto homeAddress;
    private AddressDto officeAddress;
    private List<AccountDto> accounts = new ArrayList<>();

    public UserDto() {

    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public UserDto(String emailAddress, String fullName, AddressDto homeAddress, AddressDto officeAddress, List<AccountDto> accountsDto) {
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.homeAddress = homeAddress;
        this.officeAddress = officeAddress;
        this.accounts = accountsDto;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public UserDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public AddressDto getHomeAddress() {
        return homeAddress;
    }

    public UserDto setHomeAddress(AddressDto homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public AddressDto getOfficeAddress() {
        return officeAddress;
    }

    public UserDto setOfficeAddress(AddressDto officeAddress) {
        this.officeAddress = officeAddress;
        return this;
    }

    public UserDto setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
        return this;
    }

}
