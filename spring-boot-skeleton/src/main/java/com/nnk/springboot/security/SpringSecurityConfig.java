package com.nnk.springboot.security;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableEncryptableProperties

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
        
    }
    
    //    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    //    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/*").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .defaultSuccessUrl("/bidList/list", false)
                .and()
                .oauth2Login().defaultSuccessUrl("/bidList/list")
                .and()
//                .logout(logoutConfigurer -> logoutConfigurer
//                        .logoutSuccessUrl("/login").permitAll()
//                );

//        http
//                .authorizeRequests()
//                //.antMatchers("/resources/**").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/user/**").hasRole("ADMIN")
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//
//                .oauth2Login().defaultSuccessUrl("/bidList/list",false)
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .defaultSuccessUrl("/bidList/list", false)
//                .and()
                .logout().logoutUrl("/app-logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }
    
    
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

}




