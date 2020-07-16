package jp.kobespiral.agiledev.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * JankenBasicAuthConfiguration
 */
@Configuration
@EnableWebSecurity
public class JankenBasicAuthConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user1").password("{noop}pass1").roles("USER");
    auth.inMemoryAuthentication().withUser("user2").password("{noop}pass2").roles("USER");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/janken/**").authenticated().and().csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().httpBasic();
    http.csrf().disable();// POSTリクエストが拒否されるのでdisableに
  }

}