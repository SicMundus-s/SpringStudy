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

           Person person1 = new Person("Soma name", 30);
           session.save(person1);

            //session.save(person1); // Сохранение объекта в бд

            Person person = session.get(Person.class, 1);
            //person.setName("New name"); // Изменение значения поля

            //session.delete(person); // Удаление объекта из бд

            System.out.println(person1.getId());

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }


    }
}
