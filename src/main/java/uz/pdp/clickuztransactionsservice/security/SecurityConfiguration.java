//package uz.pdp.clickuztransactionsservice.security;
//
//import lombok.SneakyThrows;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
////@EnableWebSecurity
//public class SecurityConfiguration {
//    @Bean
//    @SneakyThrows
//    public SecurityFilterChain filterChain(HttpSecurity http){
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(registry-> registry.anyRequest().permitAll());
//        return http.build();
//    }
//}
