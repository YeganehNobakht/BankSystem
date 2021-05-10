package ir.maktab.data.repository;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.Manager;
import ir.maktab.data.domains.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void create(User user);
    Optional<User> get(String emailAddress);

    void update(User user);

}
