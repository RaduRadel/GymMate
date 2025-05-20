package com.RaduRadel.GymMate.GymMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        // Mock data for testing
        UserProfile user = new UserProfile(
                "john.doe@example.com",
                "Male",
                "+1234567890",
                28,
                75.5,
                180.0
        );

        model.addAttribute("user", user);
        return "profile";
    }

    // Inner DTO
    public static class UserProfile {
        private String email;
        private String sex;
        private String phone;
        private int age;
        private double weight;
        private double height;

        public UserProfile(String email, String sex, String phone,
                           int age, double weight, double height) {
            this.email = email;
            this.sex = sex;
            this.phone = phone;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        // + getters here (or use Lombok @Data)
        public String getEmail()    { return email; }
        public String getSex()      { return sex; }
        public String getPhone()    { return phone; }
        public int    getAge()      { return age; }
        public double getWeight()   { return weight; }
        public double getHeight()   { return height; }
    }
}
