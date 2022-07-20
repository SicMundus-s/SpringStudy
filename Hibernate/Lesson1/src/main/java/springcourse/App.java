package springcourse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import springcourse.model.Item;
import springcourse.model.Person;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        // Hibernate ищет файл с названием "hibernate.properties" поэтому явно не указывается путь к файлу
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession(); // Получение сессии с нашими настройками конфигурации.

        try {
            session.beginTransaction();

            /*  Разница между Save and persist: 1. Save возращает значение первичного ключа для добавленной сущности
            * persist - не возвращает. 2. Значение первичного ключа гарантированно будет определенно после вызова Save.
            * persist - не гарантирует */
            //session.persist(person);
            //Можно заметить добавление join в запросах hibernate к БД после добавления каскадирования

            Person person = session.get(Person.class, 5);
            System.out.println(person);

            Hibernate.initialize(person.getItems()); // Подгружаем явные ленивые сущности

            session.getTransaction().commit();
            System.out.println("Конец первой сессии");

            // Открываем сессию и транзакцию снова
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            person = (Person) session.merge(person); //

            session.getTransaction().commit();

        }finally {
            sessionFactory.close();
        }




    }
}
