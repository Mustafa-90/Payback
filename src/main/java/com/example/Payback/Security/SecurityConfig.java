package com.example.Payback.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
<<<<<<< HEAD
                .antMatchers("/", "/home", "/login", "/**/*.js", "/**/*.css", "/**/*.png", "/adduser").permitAll()
//                    .antMatchers("/admin").hasRole("ADMIN")
//                    .antMatchers("/groupsTest").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/payback", true)
=======
                .antMatchers("/", "/start", "/login", "/adduser", "/**/*.js", "/**/*.css", "/**/*.png").permitAll()
//                .antMatchers("/creatorTest").hasRole("CREATOR")
//                .antMatchers("/groupsTest", "/home").hasAnyRole("USER", "CREATOR")
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/home", true)
>>>>>>> 3e486979b213c32426b733aa134304db44acd68d
                .loginPage("/login")
                .permitAll();
        httpSecurity.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }


}