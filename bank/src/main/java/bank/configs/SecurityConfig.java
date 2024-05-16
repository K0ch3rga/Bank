package bank.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/status", "/registration", "login", "/img/bg.png", "/styles/main.css").permitAll()
                .requestMatchers("/list", "/main", "/newbill", "/billadded", "/newtransfer", "/transferOk",
                        "/transferErrorNums", "/transferErrorBalance", "/withdraft", "/withdraftError", "/withdraftOk",
                        "/newdeposit", "/depositOk",
                        "/checkbalance", "/showbalance", "/printbalance").hasRole("CUSTOMER")
                .requestMatchers("/admin").hasRole("ADMIN"))
                .formLogin(t -> t.loginPage("/login").permitAll()
                        .usernameParameter("email")
                        .defaultSuccessUrl("/main")
                        .failureForwardUrl("/login?error"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
