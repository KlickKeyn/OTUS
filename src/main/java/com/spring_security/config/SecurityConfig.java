package com.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/public/**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/authenticated/**")
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                .and()
                    .authorizeRequests()
                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .and()
                    .authorizeRequests()
                    .antMatchers("/**").permitAll()
                .and()
                .formLogin()
//                    .formLogin()
                .and()
                    .logout()
                    .logoutUrl("/logout");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                    .withUser("admin").password("password").roles("ADMIN")
                .and()
                    .withUser("user").password("password").roles("USER")
                .and()
                    .withUser("manager").password("manager").roles("MANAGER", "USER")
                .and()
                    .withUser("boss").password("boss").authorities("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER");
    }
}
