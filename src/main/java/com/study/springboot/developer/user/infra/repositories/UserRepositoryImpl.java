package com.study.springboot.developer.user.infra.repositories;

import com.study.springboot.developer.user.domain.repositories.UserJpaRepository;
import com.study.springboot.developer.user.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
}
