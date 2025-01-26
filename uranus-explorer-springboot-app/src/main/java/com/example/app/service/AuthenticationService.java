package com.example.app.service;

import com.example.app.auth.CustomAuthenticationProvider;
import com.example.app.dto.LoginUserDto;
import com.example.app.dto.RegisterUserDto;
import com.example.app.entity.Role;
import com.example.app.entity.User;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        user.setCommonFields(true, new Date());

        // Fetch or create the default role (e.g., "ROLE_USER")
        Role role = roleRepository.findByRoleName("ROLE_USER")
                .orElseGet(() -> {
                    // Create the role if it doesn't exist
                    Role newRole = new Role();
                    newRole.setRoleName("ROLE_USER");
                    return roleRepository.save(newRole); // Save the newly created role
                });

        // Assign the role to the user
        user.setRoles(List.of(role)); // Assuming user has a 'roles' field

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
}
