package ir.maktab;

import ir.maktab.configuration.Config;
import ir.maktab.data.domains.Manager;
import ir.maktab.data.domains.User;
import ir.maktab.data.repository.ManagerRepository;
import ir.maktab.data.repository.ManagerRepositoryImpl;
import ir.maktab.dto.UserDto;
import ir.maktab.service.interfaces.ManagerService;
import ir.maktab.service.interfaces.UserService;
import ir.maktab.web.ManagerController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankingSystemMain {
    public static void main(String[] args) throws Exception {

        ApplicationContext iocContainer = new AnnotationConfigApplicationContext(Config.class);

        ManagerController managerController = iocContainer.getBean(ManagerController.class);
        managerController.ManagerLogin();


//
//
//        Manager manager = new Manager();
//        manager.setPassword("12345");
//        UserDto userDto = new UserDto();
//        userDto.setEmailAddress("masi@gmail.com");
//        manager.setUsername("manager");
//        UserService managerRepository = iocContainer.getBean(UserService.class);
//        managerRepository.addUser(userDto);
    }

}
