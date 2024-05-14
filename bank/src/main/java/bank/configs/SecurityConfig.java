package bank.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
        http.authorizeHttpRequests(authz ->  authz
        .requestMatchers("/", "/registration", "/list", "/status", "/main", "/newbill", "/billadded", "/newtransfer", "/transferOk",
                "/transferErrorNums", "/transferErrorBalance", "/withdraft", "/withdraftError", "/withdraftOk", "/newdeposit", "/depositOk",
                "/checkbalance", "/showbalance", "/printbalance", "/styles/main.css","/img/bg.png").permitAll()
        //.requestMatchers("").hasRole("CUSTOMER")
        .requestMatchers("/create").hasRole("ADMIN"))
        .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
