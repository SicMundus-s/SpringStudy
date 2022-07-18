package springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import springcourse.model.Item;
import springcourse.model.Person;

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

//              Person person = session.get(Person.class, 3);
//              Item item = new Item("ItemFromPerson3", person);
//              session.save(item); // Сохраняем в бд новый item у человека
//
//            /* Так же из-за кэширования со стороны hibernate лучше указать с двух сторон добавление item.
//            * Эта строчка не порождает никаких SQL запросов, просто гарантирует, что объекты в кэше соответствуют БД  */
//              person.getItems().add(item);

            Person person = new Person("Test person", 50);
            Item item = new Item("ItemTestPerson", person);
            person.setItems(new ArrayList<Item>(Collections.singletonList(item)));
            session.save(person);
            session.save(item);

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }




    }
}
