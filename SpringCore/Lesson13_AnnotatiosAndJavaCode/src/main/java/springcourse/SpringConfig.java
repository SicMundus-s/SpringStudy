package springcourse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration // Конфигурация с помощью java кода
@PropertySource("classpath:musicPlayer.properties") // Поиск значений в файле пропотаерс
public class SpringConfig {
    @Bean // Созлание бина
    public ClassicalMusic classicalMusic() {
        return new ClassicalMusic();
    }

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }

    @Bean
    public PopMusic popMusic() {
        return new PopMusic();
    }

    @Bean
    public List<Music> musicList() {
        return Arrays.asList(classicalMusic(), rockMusic(), popMusic()); // Внедрение зависимости без autowired
    }
    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(musicList());  // Внедрение зависемости без autowired
    }

    @Bean
    public Computer computer() {
        return new Computer(musicPlayer());  // Внедрение зависемости без autowired
    }
}
