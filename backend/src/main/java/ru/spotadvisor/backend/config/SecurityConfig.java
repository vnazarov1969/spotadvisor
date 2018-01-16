package ru.spotadvisor.backend.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.savedrequest.NullRequestCache;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;
import static org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors()
            .and()
            .authorizeRequests()
//            .antMatchers("/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/profile").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/home").authenticated()
            .antMatchers("/logout").authenticated()
            .and()
            .formLogin()
            .and()
            .headers().frameOptions().disable() // for h2
            .and()
            .requestCache()
            .requestCache(new NullRequestCache())
            .and()
            .sessionManagement()
            .sessionCreationPolicy(IF_REQUIRED)
            .and().csrf().disable();
  }
}