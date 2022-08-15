package spring.course.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.course.springsecurity.models.Person;

import java.util.Optional;

public interface PeopleRepositories extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
