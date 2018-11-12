package de.lengsfeld.apps.vr.controllers;

import de.lengsfeld.apps.vr.entity.VrUser;
import de.lengsfeld.apps.vr.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/vrUser")
public class UserController {


    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/profile/{userName}")
    public String showProfile(Model model, @PathVariable("userName") String userName) {
        if (!model.containsAttribute("vrUser")) {
            log.info("Loading user!");
            Optional<VrUser> userOptional = userRepository.findByUserName(userName);
            if (userOptional.get() == null) {
                throw new RuntimeException("User not found!");
            }
            model.addAttribute("vrUser", userOptional.get());
        }
        return "profile";
    }

}
