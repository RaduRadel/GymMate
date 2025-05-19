package com.RaduRadel.GymMate.GymMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/schedule")
    public String schedule() { return "schedule"; }

    @GetMapping("/trainers")
    public String trainers() { return "trainers"; }
}
