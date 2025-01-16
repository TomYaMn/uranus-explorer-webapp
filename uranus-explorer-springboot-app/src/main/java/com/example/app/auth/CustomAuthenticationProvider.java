package com.example.app.auth;

import com.example.app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String identifier = authentication.getPrincipal().toString(); // Email or username
        String password = authentication.getCredentials().toString();

        // Load user details based on whether the identifier is an email or username
        UserDetails userDetails = loadUserByEmailOrUsername(identifier);

        // Validate password
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid email/username or password");
        }

        // Return authenticated token
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private UserDetails loadUserByEmailOrUsername(String identifier) {
        // Check if the identifier is an email (simple heuristic: contains "@")
        if (identifier.contains("@")) {
            // Load user by email
            return customUserDetailsService.loadUserByEmail(identifier); // Ensure `loadUserByUsername` handles email
        } else {
            // Load user by username
            return customUserDetailsService.loadUserByUsername(identifier); // Ensure `loadUserByUsername` handles username
        }
    }
}