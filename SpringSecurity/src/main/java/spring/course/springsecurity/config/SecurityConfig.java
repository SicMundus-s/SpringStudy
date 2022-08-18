package spring.course.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            auth.userDetailsService(personDetailsService)
                    .passwordEncoder(getPasswordEncoder()); // Проверка зашифрованных паролей при входе
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Настройка Spring Security(Какая страничка отвечает за вход, ошибки и тд)
    // Конфигурируем авторизацию(Настройка прав у разных групп пользователей)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Правила авторизации
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                .anyRequest().authenticated() // Настройка авторизации -> Все не авторизированные пользователи не имеют доступа к страничка
                .and()// И... настройка кастомной странички входа
                .formLogin().loginPage("/auth/login") // Передача нашей кастомной странички логина
                .loginProcessingUrl("/process_login") // Адрес Post запроса
                .defaultSuccessUrl("/hello", true) // На какую страничку перейти в случае успешного входа
                .failureUrl("/auth/login?error") // И в случае отрицательного результата входа
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login"); // При логауте стираются куки и сессия. После переходит на страничку логина
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(); // Шифрование паролей при регистрации
    }
}
