package com.RaduRadel.GymMate.GymMate.controller;

import com.RaduRadel.GymMate.GymMate.model.Message;
import com.RaduRadel.GymMate.GymMate.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContactController {

    @Autowired private MessageRepository msgRepo;
    @GetMapping("/contact")
    public String showContactForm(@RequestParam(name="sent", required=false) String sent,
                                  Model model) {
        model.addAttribute("sent", sent != null);
        return "contact";
    }


    @PostMapping("/contact/message")
    public String submitMessage(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String content) {
        Message m = new Message();
        m.setName(name);
        m.setEmail(email);
        m.setContent(content);
        msgRepo.save(m);
        return "redirect:/contact?sent";
    }
}
