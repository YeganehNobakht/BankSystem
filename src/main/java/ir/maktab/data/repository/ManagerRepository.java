package ir.maktab.data.repository;

import ir.maktab.data.domains.Manager;

import java.util.Optional;

public interface ManagerRepository {

    public void create(Manager manager);
    public Optional<Manager> get(String username);

}
