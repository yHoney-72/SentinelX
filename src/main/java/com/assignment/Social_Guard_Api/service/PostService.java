package com.assignment.Social_Guard_Api.service;

import com.assignment.Social_Guard_Api.model.Post;
import com.assignment.Social_Guard_Api.model.User;
import com.assignment.Social_Guard_Api.repository.PostRepository;
import com.assignment.Social_Guard_Api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRepository userRepository;


    public Post createPost(Post post) {
        Optional<User>OPuser = userRepository.findById(post.getAuthor().getId());
        if(post.getAuthor()!=null && post.getAuthor().getId()!=null) {
            if (OPuser.isPresent()) {
                post.setAuthor(OPuser.get());

            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Auhtor id is not found ");
        }
        return postRepository.save(post);
    }


    public String likePost(Long postId) {
        redisService.incrementScore(postId,20);
        return "Post_Liked!!";
    }
}
