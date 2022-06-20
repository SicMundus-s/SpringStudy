package springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MusicPlayer {
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;

    private Random random = new Random();


    // IoC
    @Autowired // Делегирование спрингу вставление бина
    public MusicPlayer(@Qualifier("classicalMusic") ClassicalMusic classicalMusic,
                       @Qualifier("rockMusic") RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }

    public String playMusic(MusicGenre musicGenre) {
        switch (musicGenre) {
            case CLASSICAL:
                return classicalMusic.getSong().get(random.nextInt(3));
            case ROCK:
                return rockMusic.getSong().get(random.nextInt(3));

        }
        return "Error";
    }
}
