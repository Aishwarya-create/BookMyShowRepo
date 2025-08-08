package com.aishwarya.BookMyShow.service;

import com.aishwarya.BookMyShow.dtos.SignUpRequestDTO;
import com.aishwarya.BookMyShow.dtos.SignUpResponseDTO;
import com.aishwarya.BookMyShow.models.User;
import com.aishwarya.BookMyShow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder ;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(String name, String email, String password) {
        // Check if the user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with this email already exists login with your credentials or try with a different email.");
        }

        // Create a new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        // In a real application, you should hash the password
        user.setPassword(passwordEncoder.encode(password));

        // Save the user to the database
        return userRepository.save(user);
    }
}
