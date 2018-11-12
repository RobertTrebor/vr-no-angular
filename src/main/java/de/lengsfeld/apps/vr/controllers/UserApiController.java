package de.lengsfeld.apps.vr.controllers;

import de.lengsfeld.apps.vr.entity.VrUser;
import de.lengsfeld.apps.vr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<VrUser> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<VrUser> findByUserName(@PathVariable("userName") String userName) {
        Optional<VrUser> userOptional = userRepository.findByUserName(userName);
        return userOptional.map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VrUser> save(@RequestBody VrUser vrUser) {
        String password = passwordEncoder.encode(vrUser.getPassword());
        vrUser.setPassword(password);
        userRepository.save(vrUser);
        UriComponents uri = MvcUriComponentsBuilder
                .fromMethodCall(on(UserApiController.class).findByUserName(vrUser.getUserName())).build();
        return ResponseEntity.created(uri.toUri()).build();
    }
}
