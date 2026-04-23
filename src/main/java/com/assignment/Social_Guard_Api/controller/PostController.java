package com.assignment.Social_Guard_Api.controller;

import com.assignment.Social_Guard_Api.model.Comment;
import com.assignment.Social_Guard_Api.model.Post;
import com.assignment.Social_Guard_Api.service.CommentService;
import com.assignment.Social_Guard_Api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @PostMapping()
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
  @PostMapping("/{postId}/comments")
        public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.addComment(postId, comment);
  }
  @PostMapping("/{postId}/like")
public String likePost(@PathVariable Long postId) {
        return postService.likePost(postId);
  }
}