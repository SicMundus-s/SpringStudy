package springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Scope("prototype")
public class MusicPlayer {
    List<Music> musicGenrecList = new ArrayList<>();
    private Random random = new Random();


    // IoC
   /* public MusicPlayer(@Qualifier("classicalMusic") ClassicalMusic classicalMusic,
                       @Qualifier("rockMusic") RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }*/

    public MusicPlayer(List<Music> musicGenrecList) {
        this.musicGenrecList = musicGenrecList;
    }

    public Music playMusic() {/*
        switch (musicGenrecList.get()) {
            case :
                return classicalMusic.getSong().get(random.nextInt(3));
            case ROCK:
                return rockMusic.getSong().get(random.nextInt(3));

        }
        */
        return musicGenrecList.get(random.nextInt(musicGenrecList.size()));
    }
}
