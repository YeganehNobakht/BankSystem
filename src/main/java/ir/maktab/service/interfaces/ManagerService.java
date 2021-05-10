package ir.maktab.service.interfaces;

import ir.maktab.dto.ManagerDto;

public interface ManagerService {
    public ManagerDto managerLogin(String username, String password) throws Exception;
}
