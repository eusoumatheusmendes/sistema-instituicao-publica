package br.com.camara.legisprint.config;

import br.com.camara.legisprint.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/**", "/images/**").permitAll()
                .antMatchers("/", "/login/error", "/h2-console/**", "http://localhost:8080/h2-console/**", "/camara/cadastrar", "/login", "/camara/cadastro").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/painel/dashboard", true)
                        .failureUrl("/login/error")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
