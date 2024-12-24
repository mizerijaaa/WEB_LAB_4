package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;

    public SecurityConfig (PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/songs", "/assets/**", "/h2/**")
                        .permitAll()
                        .requestMatchers("/songs/add-form", "/songs/edit-form/**", "/songs/delete/**").hasRole("ADMIN") // Само ADMIN
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                //kreirame login forma
                .formLogin((form) -> form
                        .permitAll()
                        .defaultSuccessUrl("/songs", true)//ako e uspedna najavata se redirektira na products i sekogas /songs da e defaultna strana
                )
                //logout forma
                .logout((logout) -> logout
                        .clearAuthentication(true) //se odjavuva korisnikot
                        .invalidateHttpSession(true) //se invalidira sesijata
                        .deleteCookies("JSESSIONID")//brise cookie za korisnikot
                        .logoutSuccessUrl("/songs") //se vrakja /songs samo bez edit i
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin1234"))
                .roles("ADMIN")//ulogata na korisnikot
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}