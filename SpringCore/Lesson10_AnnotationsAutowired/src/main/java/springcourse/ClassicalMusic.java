package springcourse;

import org.springframework.stereotype.Component;

@Component // Аннотация компонент создаёт бин из класса
public class ClassicalMusic implements  Music{
    @Override
    public String getSong() {
        return "ClassicalMusic";
    }
}