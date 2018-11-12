package de.lengsfeld.apps.vr.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
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

}
