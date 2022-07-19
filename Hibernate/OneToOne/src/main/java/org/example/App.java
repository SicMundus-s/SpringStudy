package org.example;

import model.Item;
import model.Passport;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Hibernate ищет файл с названием "hibernate.properties" поэтому явно не указывается путь к файлу
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
                addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession(); // Получение сессии с нашими настройками конфигурации.

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 8);
            System.out.println(person.getPassport().getPassportNumber());

            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }



        /*
        try {
            session.beginTransaction();

            Person person = new Person("Cascanding", 25);
            person.addItem(new Item("Test casnding item1"));
            person.addItem(new Item("Test casnding item2"));
            person.addItem(new Item("Test casnding item3"));

            /*  Разница между Save and persist: 1. Save возращает значение первичного ключа для добавленной сущности
             * persist - не возвращает. 2. Значение первичного ключа гарантированно будет определенно после вызова Save.
             * persist - не гарантирует
            //session.persist(person);
            //Можно заметить добавление join в запросах hibernate к БД после добавления каскадирования
            session.save(person);



            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
        */

    }
}
