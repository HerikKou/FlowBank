package FlowBank.FlowBank.Config;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration 
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthFilter JwtAuthFilter;
    public SecurityConfig(JwtAuthFilter JwtAuthFilter) {
        this.JwtAuthFilter = JwtAuthFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable()).
        sessionManagement(session-> session.
            sessionCreationPolicy(SessionCreationPolicy.STATELESS)).exceptionHandling(exception -> exception
                .authenticationEntryPoint
                (new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).accessDeniedHandler((HttpServletRequest request, HttpServletResponse response,AccessDeniedException ex) -> {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                })
        ).authorizeHttpRequests(auth -> auth
    .requestMatchers(HttpMethod.POST,"/api/cadastrarCliente", "/api/loginCliente")
        .permitAll()

    .requestMatchers(HttpMethod.POST,"/api/loginFuncionario")
        .permitAll()

    .requestMatchers(HttpMethod.POST,"/api/cadastroFuncionario")
        .hasRole("GERENTE")

    .requestMatchers(HttpMethod.POST,"/api/aprovar")
        .hasAnyRole("FUNCIONARIO", "GERENTE")
     .requestMatchers(HttpMethod.POST,"/api/solicitacoes/{solicitacaoId}/decisao")
        .hasAnyRole("FUNCIONARIO", "GERENTE")       
    .requestMatchers(HttpMethod.POST,"/api/solicitar")
        .hasRole("CLIENTE")
    .requestMatchers(HttpMethod.POST,"/api/realizarTransacao")
        .hasRole("CLIENTE")
    .anyRequest().authenticated()
)
    
    
        .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
                
    }
}
