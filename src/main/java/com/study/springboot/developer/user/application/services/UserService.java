package com.study.springboot.developer.user.application.services;

import com.study.springboot.developer.user.application.dto.request.AddUserRequest;
import com.study.springboot.developer.user.domain.entity.User;
import com.study.springboot.developer.user.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(AddUserRequest addUserRequest) {
        userRepository.save(User.builder()
            .email(addUserRequest.getEmail())
            .password(passwordEncoder.encode(addUserRequest.getPassword()))
            .build());
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
