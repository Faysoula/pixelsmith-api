package com.pixelsmith.services;

import com.pixelsmith.Models.User;
import com.pixelsmith.repos.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User signup(User userDto) {
        // Check if user with the provided email already exists
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            throw new IllegalStateException("Email already exists");
        }

        // Check if the provided password is null
        if (userDto.getPasswordhash() == null || userDto.getPasswordhash().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Create a new user and encode the password
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordhash(passwordEncoder.encode(userDto.getPasswordhash()));

        // Save the new user to the repository
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPasswordhash())){
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
