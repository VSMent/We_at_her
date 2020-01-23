package pr.eleks.we_at_her;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pr.eleks.we_at_her.hibernate.HibernateUtil;
import pr.eleks.we_at_her.topic.Topic;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    private static void init() {
        Topic student = new Topic("Ramesssssssh", "Fadatare", "rameshfadatare@javaguides.com");
        Topic student1 = new Topic("Ja sdad sd ohn", "Cena", "john@javaguides.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Topic> topics = session.createQuery("from Topic", Topic.class).list();
            topics.forEach(t -> System.out.println(t.getName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}