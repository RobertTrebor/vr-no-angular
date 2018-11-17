package de.lengsfeld.apps.vr.configuration;


import de.lengsfeld.apps.vr.entity.VrUser;
import de.lengsfeld.apps.vr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Component
public class VrUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public VrUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<VrUser> userOptional = userRepository.findByUserName(userName);
        VrUser vrUser = null;
        if (userOptional.isPresent()) {
            vrUser = userOptional.get();
        }
        return new User(Objects.requireNonNull(vrUser).getUserName(), vrUser.getPassword(), new ArrayList<>());
    }

}
