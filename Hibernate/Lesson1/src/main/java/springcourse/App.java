package springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import springcourse.model.Person;

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
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession(); // Получение сессии с нашими настройками конфигурации.

        try {
            session.beginTransaction();

           Person person1 = new Person("Soma name", 30);
           //session.save(person1);

            //session.save(person1); // Сохранение объекта в бд

            Person person = session.get(Person.class, 1);
            //person.setName("New name"); // Изменение значения поля

            //session.delete(person); // Удаление объекта из бд


            // HQL
            /**
             * HQL - диалект SQL взаимодействующий с hibernate, он же в свою очередь переводит запрос HQL -> SQL
             * HQL обрщрается не к БД а к сущностям(Java классам)
             */

            // Оператор Like похож на регулярные выражения. Описывает паттерны в тексте
            List<Person> people = session.createQuery("FROM Person WHERE name LIKE 'S%' ").getResultList();
            for(Person temp : people) {
               System.out.println(temp);
            }
            session.createQuery("UPDATE Person SET name = 'TEST' WHERE age < 3  5").executeUpdate();

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }


    }
}
