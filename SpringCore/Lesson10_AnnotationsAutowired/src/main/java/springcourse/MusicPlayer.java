package springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
    private ClassicalMusic classicalMusic;

    // IoC
    @Autowired // Делегирование спрингу вставление бина
    public MusicPlayer(ClassicalMusic classicalMusic) {
        this.classicalMusic = classicalMusic;
    }

    public String  playMusic() {
        return "Playing: " + classicalMusic.getSong();
    }
}
