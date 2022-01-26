package com.nigga_security.config;

import com.nigga_security.config.role.ApplicationUserPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.nigga_security.config.role.ApplicationUserPermission.*;
import static com.nigga_security.config.role.ApplicationUserRole.*;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMIN_READ.name())
                .anyRequest().permitAll()
                .and()
                .httpBasic()
            .and()
                .logout()
                .logoutUrl("/logout");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("nigga")
//            .password("666")
//                .authorities("ROLE_STUDENT")
//            .roles("STUDENT");
//    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails nigga = User.builder()
                .username("nigga")
                .password(passwordEncoder.encode("666"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .roles(ADMIN.name())
                .build();

        UserDetails readAdmin = User.builder()
                .username("adminr")
                .password(passwordEncoder.encode("123"))
                .roles(ADMIN_READ.name())
                .build();

        return new InMemoryUserDetailsManager(nigga, admin, readAdmin);
    }
}
