package com.study.springboot.developer.Member.Domain.Repositories;

import com.study.springboot.developer.Member.Domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
