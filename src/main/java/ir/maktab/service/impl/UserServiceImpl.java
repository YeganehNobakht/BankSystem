package ir.maktab.service.impl;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.User;
import ir.maktab.data.repository.UserRepository;
import ir.maktab.dto.AddressDto;
import ir.maktab.dto.UserDto;
import ir.maktab.service.exceptions.checkes.InvalidEmailAddressException;
import ir.maktab.service.interfaces.UserService;
import ir.maktab.service.mapper.AccountUserMapper;
import ir.maktab.service.validation.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountUserMapper userMapper;
    private final Validations validations;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountUserMapper userMapper, Validations validations) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validations = validations;
    }


    @Override
    public void addUser(UserDto userDto) throws Exception {
        if (Objects.isNull(userDto.getEmailAddress() ))
            throw new Exception("Email address can not be null");
        User user = userMapper.toUser(userDto);
        if (validations.validateEmail(user.getEmailAddress()))
            userRepository.create(user);
    }

    @Override
    public UserDto getUser(String emailAddress) {
        Optional<User> user = userRepository.get(emailAddress);
        if (user.isPresent())
            return userMapper.toUserDto(userRepository.get(emailAddress).get());
        return null;
    }
    @Override
    public void update(UserDto userDto) throws Exception {
        if (Objects.isNull(userDto.getEmailAddress()))
            throw new Exception("id is null");

        User user = userMapper.toUser(userDto);
        userRepository.update(user);
    }
    @Override
    public void addUser(String[] splitLine,UserDto userDto) throws Exception {
        userDto.setFullName(splitLine[0].split(" ")[1]);;
        String email = splitLine[1];
        //TODO :: transfer to service
        if (getUser(email)==null)
            userDto.setEmailAddress(email);
        else
            throw new Exception("Duplicate Email addrass");
        String[] homeAddress = splitLine[2].split(" ");
        String[] officeAddress = splitLine[3].split(" ");
        AddressDto homeAddressDto = new AddressDto(homeAddress[0],homeAddress[1],homeAddress[2]);
        AddressDto officeAddressDto = new AddressDto(officeAddress[0],officeAddress[1],officeAddress[2]);
        userDto.setHomeAddress(homeAddressDto);
        userDto.setOfficeAddress(officeAddressDto);
        addUser(userDto);
        System.out.println("User "+userDto.getFullName()+" is successfully created.");
    }

}
