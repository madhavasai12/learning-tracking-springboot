package com.klef.jfsd.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.klef.jfsd.springboot.model.User;
import com.klef.jfsd.springboot.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository repo;

    // Method to insert a new user into the database
    public User insertUser(User user) {
        return repo.save(user);
    }

    // Method to get all users from the database
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // Method to authenticate user based on email and password
    public User authenticateUser(String email, String password) {
        User user = repo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Method to find a user by their email address
    public User findUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    // Method to update user details (e.g., for password reset)
    public User updateUser(User user) {
        return repo.save(user); // Save method updates the record if it already exists
    }
}
