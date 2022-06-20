package springcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // Аннотация компонент создаёт бин из класса
public class ClassicalMusic implements  Music{
    private List<String> classicalMusicList = new ArrayList<>();

    ClassicalMusic() {
        classicalMusicList.add("ClassicalMusic1");
        classicalMusicList.add("ClassicalMusic2");
        classicalMusicList.add("ClassicalMusic3");
    }
    @Override
    public List<String> getSong() {
        return classicalMusicList;
    }
}