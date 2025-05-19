package com.RaduRadel.GymMate.GymMate.controller;

import com.RaduRadel.GymMate.GymMate.model.SocialLike;
import com.RaduRadel.GymMate.GymMate.model.SocialPost;
import com.RaduRadel.GymMate.GymMate.repo.SocialLikeRepository;
import com.RaduRadel.GymMate.GymMate.repo.SocialPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Controller
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private SocialPostRepository postRepo;

    @Autowired
    private SocialLikeRepository likeRepo;

    @GetMapping({ "", "/list" })
    public String feed(Model model) {
        model.addAttribute("posts", postRepo.findAllByOrderByCreatedAtDesc());
        return "social/list";
    }

    @GetMapping("/new")
    public String form() {
        return "social/new";
    }

    @PostMapping
    public String create(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam String content,
            @AuthenticationPrincipal User user
    ) throws IOException {
        String filename = null;
        if (image != null && !image.isEmpty()) {
            Path uploadDir = Paths.get("uploads");
            if (Files.notExists(uploadDir)) Files.createDirectories(uploadDir);

            filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Files.copy(image.getInputStream(),
                    uploadDir.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        SocialPost post = new SocialPost();
        post.setUsername(user.getUsername());
        post.setImageFilename(filename);
        post.setContent(content);
        postRepo.save(post);

        return "redirect:/social";
    }

    @PostMapping("/{id}/like")
    public String like(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        postRepo.findById(id).ifPresent(p -> {
            if (!likeRepo.existsByPostAndUsername(p, user.getUsername())) {
                SocialLike like = new SocialLike();
                like.setPost(p);
                like.setUsername(user.getUsername());
                likeRepo.save(like);
                p.setLikes(p.getLikes() + 1);
                postRepo.save(p);
            }
        });
        return "redirect:/social";
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        postRepo.findById(id).ifPresent(p -> {
            if (p.getUsername().equals(user.getUsername())) {
                // remove likes and then the post
                likeRepo.deleteAllByPost(p);
                postRepo.delete(p);
            }
        });
        return "redirect:/social";
    }
}
