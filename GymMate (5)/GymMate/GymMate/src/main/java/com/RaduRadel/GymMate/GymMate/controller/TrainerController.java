package com.RaduRadel.GymMate.GymMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainerController {

    @GetMapping("/trainers")
    public String showTrainersPage() {
        return "trainers"; // matches templates/trainers.html
    }
}
