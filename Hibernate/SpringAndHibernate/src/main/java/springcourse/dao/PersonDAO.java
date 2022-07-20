package springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DAO Инкапсуляция источника данных. Проще говоря DAO урпавляет данными переданными из БД и тем самым закрывая потребность напрямую обращатся к БД
@Component
public class PersonDAO {

    // private final JdbcTemplate jdbcTemplate; // Теперь используем hibernate

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true) // Открывают транзакцию hibernate. Производит автоматический коммит после завершения метода.
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("SELECT p FROM  Person p", Person.class).getResultList();
        return people;
    }

    public Person show(int id) {
        return null;
    }

    public Optional<Person> show(String email) {
        return null;
    }

    public void save(Person person) {

    }

    public void update(int id, Person updatedPerson) {

    }

    public void delete(int id) {

    }

    ///////////////
    //Тест производительности пакетной ставки
    //////////////

    public void testMutipleUpdate() {
       List<Person> people = create1000people();

       long before = System.currentTimeMillis();

       // Обычное добавление 1000 пользователей. Делаем 1000 запросов к БД.
       for(Person person : people) {

       }

       long after = System.currentTimeMillis();

       System.out.println("time:" + (after - before));
    }


    // Добавление 1000 пользвателей с помощью BatchPreparedStatementSetter. Делаем 1 запрос к БД
    public void testBatchUpdate() {
        List<Person> people = create1000people();

        long before = System.currentTimeMillis();



        long after = System.currentTimeMillis();

        System.out.println("time:" + (after - before));
    }

    public List<Person> create1000people() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i, 30, "Email" + i + "mail.ru", "Address" + i));
        }
        return people;
    }
}