package com.RaduRadel.GymMate.GymMate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        // Sample user for demonstration (replace with dynamic data later)
        model.addAttribute("user", new UserProfile(
                "john.doe@example.com",
                "Male",
                "+1234567890",
                28,
                75.5,
                180.0
        ));
        return "profile"; // maps to templates/profile.html
    }

    // Simple placeholder class (ideally move to a separate file or service)
    static class UserProfile {
        private final String email, sex, phone;
        private final int age;
        private final double weight, height;

        public UserProfile(String email, String sex, String phone, int age, double weight, double height) {
            this.email = email;
            this.sex = sex;
            this.phone = phone;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        public String getEmail() { return email; }
        public String getSex() { return sex; }
        public String getPhone() { return phone; }
        public int getAge() { return age; }
        public double getWeight() { return weight; }
        public double getHeight() { return height; }
    }
}
