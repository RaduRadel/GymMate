package com.RaduRadel.GymMate.GymMate.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SocialPost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String imageFilename;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int likes = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }

    public String getImageFilename() { return imageFilename; }
    public void setImageFilename(String f) { this.imageFilename = f; }

    public String getContent() { return content; }
    public void setContent(String c) { this.content = c; }

    public int getLikes() { return likes; }
    public void setLikes(int l) { this.likes = l; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime t) { this.createdAt = t; }
}
