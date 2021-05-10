package ir.maktab.configuration;


import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.Manager;
import ir.maktab.data.domains.BankTransactions;
import ir.maktab.data.domains.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = {"ir.maktab"})
@PropertySource("classpath:database.properties")
public class Config {
    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }


    @Bean("sessionFactory")
    public SessionFactory getSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Manager.class);
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(BankTransactions.class);
            configuration.setProperties(new Properties() {
                {
                    put("hibernate.connection.driver_class", environment.getRequiredProperty("hibernate.connection.driver_class"));
                    put("hibernate.connection.url", environment.getRequiredProperty("hibernate.connection.url"));
                    put("hibernate.connection.username", environment.getRequiredProperty("hibernate.connection.username"));
                    put("hibernate.connection.password", environment.getRequiredProperty("hibernate.connection.password"));
                    put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
                    put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
                    put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
                }

            });

            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}
