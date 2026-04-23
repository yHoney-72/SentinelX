package com.assignment.Social_Guard_Api.repository;

import com.assignment.Social_Guard_Api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment,Long> {
}
