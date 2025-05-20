package com.RaduRadel.GymMate.GymMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassesController {
    @GetMapping("/classes")
    public String showClassesPage() {
        return "classes";
    }

}
