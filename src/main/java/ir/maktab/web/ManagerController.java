package ir.maktab.web;

import ir.maktab.dto.*;
import ir.maktab.service.impl.Generate13DigitController;
import ir.maktab.service.interfaces.AccountService;
import ir.maktab.service.interfaces.ManagerService;
import ir.maktab.service.interfaces.TransactionService;
import ir.maktab.service.interfaces.UserService;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ManagerController {
    private final ManagerService managerService;
    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public ManagerController(ManagerService managerService, Scanner scanner,
                             UserService userService,
                             AccountService accountService,
                             TransactionService transactionService) {
        this.managerService = managerService;
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public void ManagerLogin() throws Exception {
        System.out.println("Please Enter Username");
        String username = scanner.nextLine();
        System.out.println("Please Enter Password");
        String password = scanner.nextLine();
        ManagerDto managerDto = managerService.managerLogin(username, password);
        if (Objects.nonNull(managerDto)){
            System.out.println("You are successfully login.");
            System.out.println("Please enter your command line");
            String line = scanner.nextLine();
            while (!line.equalsIgnoreCase("q")) {
                String[] splitLine = line.split(",");


                UserDto userDto = new UserDto();
                if (splitLine[0].startsWith("add".toLowerCase())){
                    userService.addUser(splitLine,userDto);
                }

                if (splitLine[0].startsWith("create") || splitLine[0].startsWith("CREATE")){
                    AccountDto accountDto = new AccountDto();
                    UserDto user = userService.getUser(splitLine[0].split(" ")[1].trim());
                    if (user!=null){
                        accountService.createAccountsForUser(splitLine,accountDto,user);
                    }
                    else
                        System.out.println("Email address is not exist.");
                }

                if (splitLine[0].startsWith("Accounts".toLowerCase()) || splitLine[0].startsWith("accounts".toUpperCase())){
                    accountService.printAccounts(splitLine);
                }
                if (splitLine[0].startsWith("TRANSFER".toLowerCase()) || splitLine[0].startsWith("TRANSFER".toUpperCase())){
                    transactionService.transfer(Long.valueOf(splitLine[0].split(" ")[1].trim()),
                            Long. valueOf(splitLine[1].trim()),Double.valueOf(splitLine[2].trim()));
                    System.out.println("Transfer data was successfully done.");
                }
                if (splitLine[0].startsWith("TRANSACTIONS".toLowerCase()) || splitLine[0].startsWith("TRANSACTIONS".toUpperCase())){
                   transactionService.printTransactions(splitLine);
                }

                System.out.println("Please enter your command line");
                line = scanner.nextLine();
            }

        }
    }
}
