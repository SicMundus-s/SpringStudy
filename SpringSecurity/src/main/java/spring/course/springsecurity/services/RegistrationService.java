package spring.course.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.course.springsecurity.models.Person;
import spring.course.springsecurity.repositories.PeopleRepositories;

@Service
public class RegistrationService {

    private final PeopleRepositories peopleRepositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepositories peopleRepositories, PasswordEncoder passwordEncoder) {
        this.peopleRepositories = peopleRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER"); // Начинаю с ROLE_ так секьюрити будет понимать какую роль мы назначаем
        peopleRepositories.save(person);
    }
}
