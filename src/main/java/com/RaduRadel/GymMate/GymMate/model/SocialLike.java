package com.RaduRadel.GymMate.GymMate.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "social_like",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "username"})
)
public class SocialLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private SocialPost post;

    @Column(nullable = false)
    private String username;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public SocialPost getPost() { return post; }
    public void setPost(SocialPost post) { this.post = post; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
