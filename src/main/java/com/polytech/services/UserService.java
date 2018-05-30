package com.polytech.services;

import com.polytech.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Guillaume Bardet on 30/05/2018.
 */
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user){
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
    }

}
