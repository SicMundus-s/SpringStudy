package springcourse.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springcourse.models.Person;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        /* Проблема N + 1 - N - запросы к БД для получения связанных сущностей. 1 - запрос к таблице Person(в данном случае
        * Мы делаем N запрос к БД для получения связанных айтомов что сильно нагружает нашу БД.*/
          Session session = entityManager.unwrap(Session.class);
//
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//
//        for(Person person : people) {
//            System.out.println("Person: " + person.getName() + " Items: " + person.getItemList());
//        }


        /* Решение будет связать две таблицы и уже после делать выборку по одной конечной таблице и делать всего один запрос к БД */
        //SOLUTION N+1
        // Hash set убирает дубликаты из результирующий таблицы. Для этого нужно реализовать методы hash code and Equals
        Set<Person> people = new HashSet<Person>( session.createQuery("select p from Person p LEFT JOIN FETCH p.itemList").getResultList());


        for(Person person : people) {
           System.out.println("Person: " + person.getName() + " Items: " + person.getItemList());
        }

    }
}
