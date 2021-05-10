package ir.maktab.web;

import ir.maktab.dto.UserDto;
import ir.maktab.service.exceptions.checkes.InvalidEmailAddressException;
import ir.maktab.service.interfaces.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(UserDto userDto) throws Exception {
        userService.addUser(userDto);
    }

    public void update(UserDto userDto) throws Exception {
        userService.update(userDto);
        System.out.println("user updated");

    }
}
