package ir.maktab.service.mapper;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.User;
import ir.maktab.dto.AccountDto;
import ir.maktab.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class AccountUserMapperImpl implements AccountUserMapper{
    private final AddressMapper addressMapper;

    public AccountUserMapperImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Account toAccount(AccountDto accountDto) {
               return new Account().setId(accountDto.getId())
                .setAccountType(accountDto.getAccountType())
                .setAmount(accountDto.getAmount())
                .setUser(toUser(accountDto.getUserDto()))
                .setPassword(accountDto.getPassword())
                .setAccountNumber(accountDto.getAccountNumber());
    }

    @Override
    public AccountDto toAccountDto(Account account) {
        return new  AccountDto().setId(account.getId())
                .setAmount(account.getAmount())
                .setAccountType(account.getAccountType())
                .setUserDto(toUserDto(account.getUser()))
                .setAccountNumber(account.getAccountNumber())
                .setPassword(account.getPassword());
    }

    @Override
    public User toUser(UserDto userDto) {
        return new User().setEmailAddress(userDto.getEmailAddress()).setFullName(userDto.getFullName())
                .setHomeAddress(addressMapper.toAddress(userDto.getHomeAddress()))
                .setOfficeAddress(addressMapper.toAddress(userDto.getOfficeAddress()))
                /*.setAccounts(userDto.getAccounts().stream().map(this::toAccount).collect(Collectors.toList()))*/;
    }

    @Override
    public UserDto toUserDto(User user) {
        return new UserDto().setEmailAddress(user.getEmailAddress()).setFullName(user.getFullName())
                .setHomeAddress(addressMapper.toAddressDto(user.getHomeAddress()))
                .setOfficeAddress(addressMapper.toAddressDto(user.getOfficeAddress()))
                /*.setAccounts(user.getAccounts().stream().map(this::toAccountDto).collect(Collectors.toList()))*/;
    }
}
