package ir.maktab.data.domains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String emailAddress;

    private String fullName;


    @AttributeOverrides({
            @AttributeOverride(name = "city" , column = @Column(name="home_city")),
            @AttributeOverride(name = "street" , column = @Column(name="home_street")),
            @AttributeOverride(name = "alley" , column = @Column(name="home_alley"))
    })
    private Address homeAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "city" , column = @Column(name="office_city")),
            @AttributeOverride(name = "street" , column = @Column(name="office_street")),
            @AttributeOverride(name = "alley" , column = @Column(name="office_alley"))
    })
    private Address officeAddress;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "user",fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    public User() {

    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public User setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public User(String emailAddress, String fullName, Address homeAddress, Address officeAddress) {
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.homeAddress = homeAddress;
        this.officeAddress = officeAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public User setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public User setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
        return this;
    }
}
