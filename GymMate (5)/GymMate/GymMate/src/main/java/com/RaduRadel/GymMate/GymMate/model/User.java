package com.RaduRadel.GymMate.GymMate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    /** BCrypt-hashed password */
    @Column(nullable = false)
    private String password;

    /** One of ROLE_GUEST, ROLE_MEMBER, ROLE_ADMIN */
    @Column(nullable = false)
    private String role;

    // ─── Getters & Setters ────────────────────────────────────────────────
    public Long getId()                { return id;       }
    public void setId(Long id)         { this.id = id;     }

    public String getUsername()        { return username; }
    public void setUsername(String u)  { this.username = u;}

    public String getPassword()        { return password; }
    public void setPassword(String p)  { this.password = p;}

    public String getRole()            { return role;     }
    public void setRole(String r)      { this.role = r;   }
}
