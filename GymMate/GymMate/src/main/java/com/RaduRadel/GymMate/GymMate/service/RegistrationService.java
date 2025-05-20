package com.RaduRadel.GymMate.GymMate.service;

import com.RaduRadel.GymMate.GymMate.model.User;
import com.RaduRadel.GymMate.GymMate.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;

    /** registers with chosen role: ROLE_GUEST, ROLE_MEMBER, or ROLE_ADMIN */
    public void register(String username, String rawPassword, String role) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.setRole("ROLE_" + role);
        repo.save(u);
    }
}
