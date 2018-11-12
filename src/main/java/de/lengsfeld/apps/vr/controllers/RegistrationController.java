package de.lengsfeld.apps.vr.controllers;

import de.lengsfeld.apps.vr.entity.VrUser;
import de.lengsfeld.apps.vr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/vrUser")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("vrUser", new VrUser());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid VrUser vrUser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vrUser", vrUser);
            model.addAttribute("errors", bindingResult.getFieldError());
            return "register";
        }
        String password = passwordEncoder.encode(vrUser.getPassword());
        vrUser.setPassword(password);
        userRepository.save(vrUser);
        redirectAttributes.addFlashAttribute("vrUser", vrUser);
        return "redirect:/vrUser/profile/" + vrUser.getUserName();
    }

}
