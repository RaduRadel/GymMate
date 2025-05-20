package com.RaduRadel.GymMate.GymMate.controller;

import com.RaduRadel.GymMate.GymMate.model.Message;
import com.RaduRadel.GymMate.GymMate.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/messages")
public class AdminController {

    @Autowired private MessageRepository msgRepo;

    @GetMapping
    public String inbox(Model model) {
        List<Message> msgs = msgRepo.findByResolvedFalseOrderByCreatedAtDesc();
        model.addAttribute("msgs", msgs);
        return "admin/messages";
    }

    @PostMapping("/{id}/resolve")
    public String resolve(@PathVariable Long id) {
        msgRepo.findById(id).ifPresent(m -> {
            m.setResolved(true);
            msgRepo.save(m);
        });
        return "redirect:/admin/messages";
    }
}
