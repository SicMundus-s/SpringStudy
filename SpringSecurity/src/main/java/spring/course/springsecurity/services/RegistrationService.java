package spring.course.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.course.springsecurity.models.Person;
import spring.course.springsecurity.repositories.PeopleRepositories;

@Service
public class RegistrationService {

    private final PeopleRepositories peopleRepositories;

    @Autowired
    public RegistrationService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    @Transactional
    public void register(Person person) {
        peopleRepositories.save(person);
    }
}
