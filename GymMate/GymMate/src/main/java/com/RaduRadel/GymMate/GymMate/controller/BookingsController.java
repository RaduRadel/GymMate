package com.RaduRadel.GymMate.GymMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingsController {

    @GetMapping("/bookings")
    public String showBookingsPage() {
        return "bookings"; // maps to templates/bookings.html
    }
}
