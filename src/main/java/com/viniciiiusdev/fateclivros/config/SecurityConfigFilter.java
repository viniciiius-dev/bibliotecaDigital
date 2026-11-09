    package com.viniciiiusdev.fateclivros.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.SecurityFilterChain;

    import static org.springframework.security.config.Customizer.withDefaults;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfigFilter {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // Desativa CSRF para APIs REST
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/users/register", "/api/books/**", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**", "/index.html", "/css/**", "/js/**", "/imgs/**", "/scripts/**", "/imgs/**").permitAll()                            .anyRequest().authenticated()
                    ).formLogin(form -> form
                            // Se a sua página se chama index.html ou login.html na raiz do static:
                            .loginPage("/index.html")
                            .loginProcessingUrl("/login") // O formulário HTML faz POST para cá
                            .defaultSuccessUrl("/pagina-inicial", true) // Rota para onde vai após logar com sucesso
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/index.html?logout=true")
                            .permitAll()
                    ).headers(headers -> headers
                            .frameOptions(frameOptions -> frameOptions.sameOrigin())
                    );

            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }
    }