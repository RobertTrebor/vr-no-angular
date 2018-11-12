package de.lengsfeld.apps.vr.Configuration;


import de.lengsfeld.apps.vr.entity.VrUser;
import de.lengsfeld.apps.vr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class VrUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<VrUser> userOptional = userRepository.findByUserName(userName);
        VrUser vrUser = userOptional.get();
        return new User(vrUser.getUserName(), vrUser.getPassword(), new ArrayList<>());
    }

}
