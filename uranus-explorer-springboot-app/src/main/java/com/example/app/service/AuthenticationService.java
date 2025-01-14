package com.example.app.service;

import com.example.app.dto.LoginUserDto;
import com.example.app.dto.RegisterUserDto;
import com.example.app.auth.CustomUserDetails;
import com.example.app.entity.Role;
import com.example.app.entity.User;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // RoleRepository to manage roles
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository, // Inject RoleRepository
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + input.getEmail()));

        return new CustomUserDetails(user).getUser(); // Wrap User in CustomUserDetails
    }
}
