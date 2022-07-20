package springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import springcourse.model.Actor;
import springcourse.model.MovieManyToMany;

import java.util.ArrayList;
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
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(MovieManyToMany.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try(sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            MovieManyToMany movieManyToMany = new MovieManyToMany("Pulp fiction", 1994);
            Actor actor = new Actor("Harvey Ketel", 81);
            Actor actor1 = new Actor("Samuel L. Jackson", 72);

            movieManyToMany.setActorList(new ArrayList<>(List.of(actor, actor1))); // List.of - неизменяемый список, но расширяемый.
            // но поскольку он помещается в ArrayList, он становится обычным Листом(изменяемым/дополняемым)

            actor.setMovieManyToManyList(new ArrayList<>(Collections.singletonList(movieManyToMany)));
            actor1.setMovieManyToManyList(new ArrayList<>(Collections.singletonList(movieManyToMany)));

            session.save(movieManyToMany);
            session.save(actor);
            session.save(actor1);


            session.getTransaction().commit();
        }




    }
}
