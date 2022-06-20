package springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer {
    private int id;
    private MusicPlayer musicPlayer;

    @Autowired // Автоматически внедряет бин подходящий по значению
    public Computer(MusicPlayer musicPlayer) {
        this.id = 1;
        this.musicPlayer = musicPlayer;
    }
    public String toString() {
        return "Computer id: " + id + " " + musicPlayer.playMusic(MusicGenre.CLASSICAL) + '\n'
         + "Computer id: " + id + " " + musicPlayer.playMusic(MusicGenre.ROCK);
    }
}
