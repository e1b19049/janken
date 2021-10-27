package oit.is.z1204.first.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Lec03AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // pass = admin
    auth.inMemoryAuthentication().withUser("admin")
        .password("$2y$10$y22POi3i0LN1DTDZ/XdFpeiY0cauPuydEeTgsRQtm7XrKaXwdIHfW").roles("ADMIN");

    // pass = user
    auth.inMemoryAuthentication().withUser("user")
        .password("$2y$10$qFJxTZjJMFWF5o7gzBlZ1ukYwdMx3QICbbGMFDpD2WQ53y7u33Hsa").roles("USER");

    // pass = test
    auth.inMemoryAuthentication().withUser("test")
        .password("$2y$10$lXgNIN6RlIOxF.PJeAIFDOiqP.uWCRK3EfdNQRe9M2qMzFLWywEK6").roles("USER");

    // pass = honda
    auth.inMemoryAuthentication().withUser("ほんだ")
        .password("$2y$10$KLLuFTbkwJaQ50b2hdDIjeIFqWAxfIyV4gRGgWu7mu/TtL2OwjpJi").roles("USER");

    // pass = igaki
    auth.inMemoryAuthentication().withUser("いがき")
        .password("$2y$10$.YQLA8l0lXCsbWQEL8qBe.lKLs3Cvlgb8Q5iJ1caEs3VYGnzPHTEi").roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();

    http.authorizeRequests().antMatchers("/lec02/**").authenticated();

    // Spring Securityの機能を利用してログアウト．ログアウト時は http://localhost:8080/ に戻る
    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
