package ir.maktab.dto;

import ir.maktab.data.domains.Manager;

public class ManagerDto {
    private String username;
    private String password;

    public ManagerDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public ManagerDto() {

    }

    public String getUsername() {
        return username;
    }

    public ManagerDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ManagerDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
