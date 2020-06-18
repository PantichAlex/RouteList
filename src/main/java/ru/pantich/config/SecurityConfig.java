package ru.pantich.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.pantich.repo.UserRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserRepo userRepo;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/register").permitAll()
                    .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/")
                .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/");
    }
}
