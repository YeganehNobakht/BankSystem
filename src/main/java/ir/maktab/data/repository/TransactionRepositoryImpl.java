package ir.maktab.data.repository;

import ir.maktab.data.domains.Account;
import ir.maktab.data.domains.BankTransactions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final SessionFactory sessionFactory;

    public TransactionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void saveTransaction(Account sourceAccount, Account destAccount, Double amount) {
        Session session = sessionFactory.openSession();
        BankTransactions bankTransactions = new BankTransactions() ;
        bankTransactions.setAmount(amount);
        bankTransactions.setDestinationAccount(destAccount);
        bankTransactions.setSourceAccount(sourceAccount);
        bankTransactions.setTransferDate(new Date());
        session.save(bankTransactions);
        session.close();
    }

    @Override
    public List<BankTransactions> getTransaction(Date date, Integer max) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(BankTransactions.class,"b");
        Date currentDate1 = new Date();
        if (date!=null) {
            criteria.add(Restrictions.ge("b.transferDate", date))
                    .add(Restrictions.lt("b.transferDate", currentDate1))
                    .setMaxResults(max);
        }

        List list =  criteria.list();
        transaction.commit();
        session.close();
        return list;

    }
//    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
//    String myDate = "17-04-2011";
//
//    Date minDate = formatter.parse(myDate);
//
//    Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
//    Conjunction and = Restrictions.conjunction();
//
//    and.add( Restrictions.ge("orderDate", minDate) );
//
//    and.add( Restrictions.lt("orderDate", maxDate) );
}
