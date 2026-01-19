package com.felicita.config;

import com.felicita.exception.UnAuthenticateErrorExceptionHandler;
import com.felicita.filter.JwtFilter;
import com.felicita.security.model.CustomUserDetails;
import com.felicita.security.model.User;
import com.felicita.util.AuthorizeEndPoints;
import com.felicita.util.PublicEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        // DESTINATIONS
                        .requestMatchers("/v0/api/destination/add-destination").hasAuthority("DESTINATION_CREATE")
                        .requestMatchers("/v0/api/destination/update-destination").hasAuthority("DESTINATION_UPDATE")
                        .requestMatchers("/v0/api/destination/terminate-destination").hasAuthority("DESTINATION_TERMINATE")
                        // ACTIVITIES
                        .requestMatchers("/api/v0/activities/add-activity").hasAuthority("ACTIVITY_CREATE")
                        .requestMatchers("/api/v0/activities/update-activity").hasAuthority("ACTIVITY_UPDATE")
                        .requestMatchers("/api/v0/activities/terminate-activity").hasAuthority("ACTIVITY_TERMINATE")
                        // TOURS
                        .requestMatchers("/v0/api/tour/add-tour").hasAuthority("TOUR_CREATE")
                        .requestMatchers("/v0/api/tour/update-tour").hasAuthority("TOUR_UPDATE")
                        .requestMatchers("/v0/api/tour/terminate-tour").hasAuthority("TOUR_TERMINATE")
                        // PACKAGES
                        .requestMatchers("/v0/api/package/add-package").hasAuthority("PACKAGE_CREATE")
                        .requestMatchers("/v0/api/package/update-package").hasAuthority("PACKAGE_UPDATE")
                        .requestMatchers("/v0/api/package/terminate-package").hasAuthority("PACKAGE_TERMINATE")

                        //LINK BAR
//                        .requestMatchers("/api/v0/link-bar/all").hasAuthority("LINK_BAR_VIEW")
//                        .requestMatchers("/api/v0/link-bar/active").hasAuthority("LINK_BAR_VIEW")

                        // LINK BAR
//                        .requestMatchers("/api/v0/nav-bar/all").hasAuthority("NAV_BAR_VIEW")
//                        .requestMatchers("/api/v0/nav-bar/active").hasAuthority("NAV_BAR_VIEW")

                        // Hero Section
//                        .requestMatchers("/api/v0/hero-section/home-all").hasAuthority("HERO_SECTION_HOME_VIEW")
//                        .requestMatchers("/api/v0/hero-section/home").hasAuthority("HERO_SECTION_HOME_VIEW")
//                        .requestMatchers("/api/v0/hero-section/about-us").hasAuthority("HERO_SECTION_ABOUT_US_VIEW")
//                        .requestMatchers("/api/v0/hero-section/contact-us").hasAuthority("HERO_SECTION_CONTACT_US_VIEW")
//                        .requestMatchers("/api/v0/hero-section/blog").hasAuthority("HERO_SECTION_BLOG_VIEW")
//                        .requestMatchers("/api/v0/hero-section/faq").hasAuthority("HERO_SECTION_FAQ_VIEW")
//                        .requestMatchers("/api/v0/hero-section/tour").hasAuthority("HERO_SECTION_TOUR_VIEW")
//                        .requestMatchers("/api/v0/hero-section/package").hasAuthority("HERO_SECTION_PACKAGE_VIEW")
//                        .requestMatchers("/api/v0/hero-section/package-schedule/{packageScheduleId}").hasAuthority("HERO_SECTION_PACKAGE_SCHEDULE_VIEW")
//                        .requestMatchers("/api/v0/hero-section/booked-tour/{bookingId}").hasAuthority("HERO_SECTION_BOOKED_TOUR_VIEW")
//                        .requestMatchers("/api/v0/hero-section/destination").hasAuthority("HERO_SECTION_DESTINATION_VIEW")
//                        .requestMatchers("/api/v0/hero-section/activity").hasAuthority("HERO_SECTION_ACTIVITY_VIEW")

                        // Why Choose Us Section
//                        .requestMatchers("/api/v0/why-choose-us/all").hasAuthority("WHY_CHOOSE_US_VIEW")
//                        .requestMatchers("/api/v0/why-choose-us/active").hasAuthority("WHY_CHOOSE_US_VIEW")

                        // Our Service Section
//                        .requestMatchers("/api/v0/our-service/all").hasAuthority("OUR_SERVICES_VIEW")
//                        .requestMatchers("/api/v0/our-service/active").hasAuthority("OUR_SERVICES_VIEW")


                                .requestMatchers(PublicEndpoints.ENDPOINTS).permitAll()
                        .requestMatchers(AuthorizeEndPoints.ENDPOINTS).authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:3001"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
