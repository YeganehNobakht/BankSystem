package ir.maktab.data.repository;

import org.hibernate.SessionFactory;
import ir.maktab.data.domains.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class ManagerRepositoryImpl implements ManagerRepository  {

    private final SessionFactory sessionFactory;

    public ManagerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Manager manager) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(manager);
        tx.commit();
        session.close();
    }


    @Override

    public Optional get(String id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        javax.persistence.Query query  = session.createQuery("from ir.maktab.data.domains.Manager as c  where c.username = :c_id")
                .setParameter("c_id",id);
        Optional teacher =  query.getResultList().stream().findFirst();
        transaction.commit();
        session.close();
        return teacher;
    }


//    public Optional<Manager> get(String username) {
//        Session session = this.sessionFactory.openSession();
//        Transaction t = session.beginTransaction();
//
//        Manager manager = session.find(Manager.class, username);
//
//        t.commit();
//        session.close();
//        return Optional.of(manager);
//    }
}
