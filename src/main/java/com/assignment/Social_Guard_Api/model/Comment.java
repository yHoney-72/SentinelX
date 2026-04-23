package com.assignment.Social_Guard_Api.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne @JoinColumn(name= "bot_id")
    private Bot bot;
    @Column(nullable = false)
    private String authorType;

    @Column(nullable = false)
    private String content;

    private int depthLevel;

    private LocalDateTime createdAt;

    @PrePersist
    public void setTime() {
        this.createdAt = LocalDateTime.now();
    }
}