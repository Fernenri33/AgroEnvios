package com.AgroEnvios.apiAE.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/hola").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/crearUsuario").permitAll()
                        .requestMatchers("/api/todosLosUsuarios").permitAll()
                        .requestMatchers("/api/usuario/{id}").permitAll()
                        .requestMatchers("/api/test/verifica").permitAll()
                        .requestMatchers("/api/getTodosLosEnvios").permitAll()
                        .requestMatchers("/api/getMisEnvios").permitAll()
                        .requestMatchers("/api/crearEnvio").permitAll()
                        .requestMatchers("/api/aceptarEnvio").permitAll()
                        .requestMatchers("/api/eliminarEnvio").permitAll()
                        .requestMatchers("/api/crearEnvioVacio").permitAll()
                        .requestMatchers("/api/getTodosLosProductos").permitAll()
                        .requestMatchers("/api/getEnvioDetalles/{idEnvio}").permitAll()
                        .requestMatchers("/api/envioDetalle/crear").permitAll()
                        .requestMatchers("/api/envioDetalle/eliminar/{idEnvioDetalle}").permitAll()
                        .requestMatchers("/api/enviarEnvio/{idEnvio}").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    // 👇 este bean define la configuración CORS permitida
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // tu frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // si usás cookies o Authorization headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // aplica a todos los endpoints
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
