package MyList.Server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
//                .csrf().disable()

                .authorizeRequests()
                .anyRequest().permitAll();
//                .antMatchers("/**").permitAll();


//                .and()
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/");
//                .usernameParameter("userId")
//                .passwordParameter("password");
//                .loginProcessingUrl("/user/login")
//
//                .and()
//                .logout()
//                .logoutUrl("/doLogout")
//                .logoutSuccessUrl("/login");


        return http.build();
    }
}
