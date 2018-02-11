
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


@SuppressWarnings("unchecked")
public class Main {
   private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        Main main =  new Main();


        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<CFapp> list = session.createQuery("from CFapp").list();

        transaction.commit();
        session.close();
        for (CFapp app: list) {
            System.out.println(app);
        }
    }
}
