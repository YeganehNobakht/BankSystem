package ir.maktab.service.interfaces;

import ir.maktab.dto.UserDto;
import ir.maktab.service.exceptions.checkes.InvalidEmailAddressException;

public interface UserService {
    void addUser(UserDto userDto) throws Exception;
    UserDto getUser(String emailAddress);

    void update(UserDto userDto) throws Exception;
    void addUser(String[] splitLine,UserDto userDto) throws Exception;
}
