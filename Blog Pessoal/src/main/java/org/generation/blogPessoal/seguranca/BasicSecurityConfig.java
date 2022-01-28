package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
        .antMatchers("/usuarios/logar").permitAll() //Libera os endpoints usuários/logar
        .antMatchers("/usuarios/cadastrar").permitAll() //Libera os endpoints usuários/cadastrar
        .anyRequest().authenticated() //Todas as outras deveram passar a chave
        .and().httpBasic() //Padrão Basic para gerar chave token
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Não guarda sessão
        .and().cors() //habilita o cors
        .and().csrf().disable(); // desabilita o csrf, para usar as configurações padrões.
    }
}