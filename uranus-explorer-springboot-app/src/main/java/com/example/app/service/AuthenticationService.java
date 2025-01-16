package com.example.app.service;

import com.example.app.auth.CustomAuthenticationProvider;
import com.example.app.dto.LoginUserDto;
import com.example.app.dto.RegisterUserDto;
import com.example.app.auth.CustomUserDetails;
import com.example.app.entity.Role;
import com.example.app.entity.User;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // RoleRepository to manage roles
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository, // Inject RoleRepository
            CustomAuthenticationProvider customAuthenticationProvider,
            PasswordEncoder passwordEncoder
    ) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        // Check for duplicate username or email
        if (userRepository.existsByUsername(input.getUserName())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(input.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Create a new user
        User user = new User();
        user.setUsername(input.getUserName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        // Fetch or create the default role (e.g., "ROLE_USER")
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    // Create the role if it doesn't exist
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole); // Save the newly created role
                });

        // Assign the role to the user
        user.setRoles(Set.of(role)); // Assuming user has a 'roles' field

        // Save the user and return
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if ((input.getEmail() == null || input.getEmail().isEmpty()) &&
                (input.getUserName() == null || input.getUserName().isEmpty())) {
            throw new IllegalArgumentException("Email or Username must be provided");
        }

        String identifier = (input.getEmail() != null) ? input.getEmail() : input.getUserName();

        customAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(identifier, input.getPassword())
        );

        User user = (input.getEmail() != null) ?
                userRepository.findByEmail(input.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + input.getEmail())) :
                userRepository.findByUsername(input.getUserName())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + input.getUserName()));

        return user; // Return the user entity directly
    }

//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String usernameOrEmail = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        // Check if the usernameOrEmail is an email
//        boolean isEmail = usernameOrEmail.contains("@");
//
//        UserDetails userDetails = null;
//        if (isEmail) {
//            // If it's an email, authenticate using the email field
//            userDetails = CustomUserDetailsService.loadUserByUsername(usernameOrEmail);
//        } else {
//            // If it's a username, authenticate using the username field
//            userDetails = CustomUserDetailsService.loadUserByUsername(usernameOrEmail);
//        }
//
//        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        // Create and return the authentication token
//        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//    }

}
