package com.RaduRadel.GymMate.GymMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/classes")
    public String classes() { return "classes"; }

    @GetMapping("/home/trainers")
    public String trainers() { return "trainers"; }
}
