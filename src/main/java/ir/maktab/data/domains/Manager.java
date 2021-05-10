package ir.maktab.data.domains;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manager {
    @Id
    private String username;
    private String password;

    public Manager() {
    }

    public String getUsername() {
        return username;
    }

    public Manager setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Manager setPassword(String password) {
        this.password = password;
        return this;
    }
}
