package ir.maktab.data.repository;

import ir.maktab.data.domains.Account;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final SessionFactory sessionFactory;

    public AccountRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveAccount(Account account) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(account);
        tx.commit();
        session.close();
    }



    @Override
    @Deprecated
    public List<Account> cityAccount(String city, Double min, Double max) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Account.class,"a");
        criteria.createAlias("a.user","u");
        if (city!=null)
            criteria.add(Restrictions.eq("u.homeAddress.city",city));
        if (max!=null && min!=null)
            criteria.add(Restrictions.between("a.amount",min,max));
        List list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(Long accountNumber) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        javax.persistence.Query query  = session.createQuery("from ir.maktab.data.domains.Account as c  where c.accountNumber = :c_accountNumber")
                .setParameter("c_accountNumber",accountNumber);
        Optional account =  query.getResultList().stream().findFirst();
        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public Optional<Account> getAccountByAmount(Double amount) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        javax.persistence.Query query  = session.createQuery("from ir.maktab.data.domains.Account as c  where c.amount = :c_amount")
                .setParameter("c_amount",amount);
        Optional account =  query.getResultList().stream().findFirst();
        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public void update(Account account) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(account);
        tx.commit();
        session.close();
    }
}
