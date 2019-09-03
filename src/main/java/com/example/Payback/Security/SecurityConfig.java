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
                .antMatchers("/", "/start", "/login", "/adduser", "/policy", "/contact", "/**/*.js", "/**/*.css", "/**/*.png").permitAll()
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
//                .antMatchers("/creatorTest").hasRole("CREATOR")
//                .antMatchers("/groupsTest", "/home").hasAnyRole("USER", "CREATOR")
                .and()
                .formLogin().defaultSuccessUrl("/home", true)
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