package springcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RockMusic implements Music{

    private List<String> rockMusicList = new ArrayList<>();

    RockMusic() {
        rockMusicList.add("Rock music1");
        rockMusicList.add("Rock music2");
        rockMusicList.add("Rock music3");
    }
    @Override
    public List<String> getSong() {
        return rockMusicList;
    }
}
