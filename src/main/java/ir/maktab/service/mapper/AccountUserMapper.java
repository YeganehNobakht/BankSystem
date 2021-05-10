package ir.maktab.service.mapper;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.User;
import ir.maktab.dto.AccountDto;
import ir.maktab.dto.UserDto;

public interface AccountUserMapper {
    Account toAccount(AccountDto accountDto);
    AccountDto toAccountDto(Account account);
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}
