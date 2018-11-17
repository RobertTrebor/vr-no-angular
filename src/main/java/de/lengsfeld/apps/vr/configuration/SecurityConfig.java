package de.lengsfeld.apps.vr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Override
    protected void normal_configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/cemeteries**/*").authenticated()
                .antMatchers("/cemeteries").authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/*", "/api/**")
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").permitAll();
    }

}
