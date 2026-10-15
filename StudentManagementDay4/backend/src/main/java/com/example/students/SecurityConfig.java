package com.example.students;
import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;import org.springframework.security.config.Customizer;import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;import org.springframework.web.cors.CorsConfigurationSource;import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
@Configuration public class SecurityConfig {
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtFilter)throws Exception{
  return http.csrf(csrf->csrf.disable()).cors(Customizer.withDefaults()).headers(h->h.frameOptions(f->f.sameOrigin()))
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a.requestMatchers("/api/auth/login","/h2-console/**").permitAll().requestMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest().authenticated())
   .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class).build();
 }
 @Bean CorsConfigurationSource corsConfigurationSource(){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of("http://localhost:5173"));c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;}
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
