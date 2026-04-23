package com.assignment.Social_Guard_Api.repository;

import com.assignment.Social_Guard_Api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Long> {
}
