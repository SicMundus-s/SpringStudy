package org.example;

import model.School;
import model.SchoolDirector;
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
        Configuration configuration = new Configuration().addAnnotatedClass(School.class).
                addAnnotatedClass(SchoolDirector.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession(); // Получение сессии с нашими настройками конфигурации.

        try {
            session.beginTransaction();


            SchoolDirector schoolDirector1 = session.get(SchoolDirector.class, 6);
            School school = session.get(School.class, 2);

            school.setSchoolDirector(schoolDirector1); // Вызовет ошибку. Нельзя добавлять больше одного директора на школу.


            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
    }
}
