package com.assignment.Social_Guard_Api.service;

import com.assignment.Social_Guard_Api.model.User;
import com.assignment.Social_Guard_Api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
       return userRepository.save(user);
    }
}
