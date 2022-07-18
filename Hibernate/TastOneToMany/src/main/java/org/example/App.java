package org.example;

import model.Director;
import model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();


//            Director director = new Director("Nikita Volkov", 2001);
//            Movie movie = new Movie("MyFilm", 2022, director);
//            director.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(director);
//            session.save(movie);

              Director director = session.get(Director.class, 7);
              Movie movie = session.get(Movie.class, 1);

              movie.setOwner(director);
              director.getMovies().add(movie);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }
}
