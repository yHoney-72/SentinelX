package com.assignment.Social_Guard_Api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String personaDescription;
}