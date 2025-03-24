package com.study.springboot.developer.user.domain.repositories;

import com.study.springboot.developer.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
