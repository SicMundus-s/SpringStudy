package springcourse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Scope("prototype") // Область видимость бинов Singlton - бин указывает на один и тот же участок памяти // prototype -
// наобор создаёт каждый бин как отдельный объект
public class ClassicalMusic implements  Music{
    private List<String> classicalMusicList = new ArrayList<>();

    // @PostConstruct Вызывает после констукртор
    public void doMyInit() {
        System.out.println("Bin create");
    }

    // @PreDestroy Вызывается при уничтожение бина
    public void doMyDestroy() {
        System.out.println("Bin destroy");
    }

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