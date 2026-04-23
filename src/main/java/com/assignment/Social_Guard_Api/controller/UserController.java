package com.assignment.Social_Guard_Api.controller;

import com.assignment.Social_Guard_Api.model.User;
import com.assignment.Social_Guard_Api.repository.UserRepository;
import com.assignment.Social_Guard_Api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
    private UserService userService;
@PostMapping
public User createUser (@RequestBody User  user) {
    return  userService.createUser(user);
}
}
