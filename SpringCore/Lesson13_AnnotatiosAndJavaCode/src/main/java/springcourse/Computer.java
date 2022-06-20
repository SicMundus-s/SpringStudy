package springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;


public class Computer {
    private int id;
    private MusicPlayer musicPlayer;

    Random random = new Random();

    public Computer(MusicPlayer musicPlayer) {
        this.id = 1;
        this.musicPlayer = musicPlayer;
    }
    public String toString() {
        return "Computer id: " + id + " " + musicPlayer.playMusic().getSong().get(random.nextInt(3)) + '\n'
         + "Computer id: " + id + " " + musicPlayer.playMusic().getSong().get(random.nextInt(3));
    }
}
