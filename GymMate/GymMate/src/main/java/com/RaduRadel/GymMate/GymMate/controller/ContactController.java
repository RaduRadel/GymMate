package com.RaduRadel.GymMate.GymMate.controller;

import com.RaduRadel.GymMate.GymMate.model.Message;
import com.RaduRadel.GymMate.GymMate.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired private MessageRepository msgRepo;

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
