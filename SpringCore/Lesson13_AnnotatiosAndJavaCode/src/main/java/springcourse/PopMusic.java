package springcourse;

import java.util.ArrayList;
import java.util.List;

public class PopMusic implements Music {
    List<String> popMusicList = new ArrayList<>();

    PopMusic() {
        popMusicList.add("Pop Music1");
        popMusicList.add("Pop Music2");
        popMusicList.add("Pop Music3");
    }


    @Override
    public List<String> getSong() {
        return popMusicList;
    }
}
