package springcourse;

import org.springframework.stereotype.Component;

@Component // Аннотация компонент создаёт бин из класса
public class ClassicalMusic {
    public String getSong() {
        return "ClassicalMusic";
    }
}