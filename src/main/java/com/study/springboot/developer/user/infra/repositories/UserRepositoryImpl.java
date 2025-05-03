package com.study.springboot.developer.user.infra.repositories;

import com.study.springboot.developer.user.domain.entity.User;
import com.study.springboot.developer.user.domain.repositories.UserJpaRepository;
import com.study.springboot.developer.user.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
