package spring.course.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.course.springsecurity.services.PersonDetailsService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // Настраиваем логику аутентификации
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(personDetailsService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Настройка Spring Security(Какая страничка отвечает за вход, ошибки и тд)
    // Конфигурируем авторизацию(Настройка прав у разных групп пользователей)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Правила авторизации
        http.authorizeRequests()
                .antMatchers("/auth/login", "/error").permitAll()
                .anyRequest().authenticated() // Настрока авторизации -> Все не авторизированные пользователи не имеют доступа к страничка
                .and()// И... настройка кастомной странички входа
                .formLogin().loginPage("/auth/login") // Передача нашей кастомной странички логина
                .loginProcessingUrl("/process_login") // Адрес Post запроса
                .defaultSuccessUrl("/hello", true) // На какую страничку перейти в случае успешного входа
                .failureUrl("/auth/login?error"); // И в случае отрицательного результата входа
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
