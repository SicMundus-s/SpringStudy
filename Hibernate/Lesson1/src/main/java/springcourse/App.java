package springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import springcourse.model.Person;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        // Hibernate ищет файл с названием "hibernate.properties" поэтому явно не указывается путь к файлу
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession(); // Получение сессии с нашими настройками конфигурации.

        try {
            session.beginTransaction();

           Person person1 = new Person("Test1", 30);
            Person person2 = new Person("Test2", 30);
            Person person3 = new Person("Test3", 30);

            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
    }
}
