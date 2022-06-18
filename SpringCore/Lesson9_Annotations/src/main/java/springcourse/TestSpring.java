package springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Music rockMusic = context.getBean("rockMusic", Music.class);

        MusicPlayer musicPlayer = new MusicPlayer(rockMusic);

        musicPlayer.playMusic();

        context.close();
    }
}
