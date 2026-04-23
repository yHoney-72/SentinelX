package com.assignment.Social_Guard_Api.repository;

import com.assignment.Social_Guard_Api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
