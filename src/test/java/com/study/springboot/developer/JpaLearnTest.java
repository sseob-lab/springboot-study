package com.study.springboot.developer;

import com.study.springboot.developer.Member.Domain.Entity.Member;
import com.study.springboot.developer.Member.Domain.Repositories.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaLearnTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Sql("/members.sql")
    @Test
    public void selectTest() {
        Optional<Member> member = memberRepository.findById(1L);

        assertThat(member.isPresent()).isEqualTo(true);
        assertThat(member.get().getId()).isEqualTo(1L);
        assertThat(member.get().getName()).isEqualTo("shim");
    }

    @Sql("/members.sql")
    @Test
    public void selectAllTest() {
        List<Member> members = memberRepository.findAll();

        assertThat(members.get(0).getName()).isEqualTo("shim");
        assertThat(members.get(1).getName()).isEqualTo("yosub");
        assertThat(members.get(2).getName()).isEqualTo("hyonsub");
    }

    @Test
    public void insertTest() {
        Member member = new Member(1L, "testMember");
        memberRepository.save(member);
        Optional<Member> foundMember = memberRepository.findById(1L);
        assertThat(foundMember.isPresent()).isEqualTo(true);
        assertThat(foundMember.get().getId()).isEqualTo(1L);
        assertThat(foundMember.get().getName()).isEqualTo("testMember");
    }

    @Test
    public void insertAllTest() {
        List<Member> members = List.of(
                new Member(1L, "hyon"),
                new Member(2L, "sub")
        );

        memberRepository.saveAll(members);

        Long count = memberRepository.count();

        assertThat(count).isEqualTo(2);
    }

    @Sql("/members.sql")
    @Test
    public void firstLevelCacheTest1() {
        Optional<Member> member = memberRepository.findById(1L); // member객체가 1차캐쉬에 캐쉬됨
        Member foundMember = member.get();
        foundMember.setName("Dan"); // 1차캐쉬의 member.name = Dan 으로 변경

        // 위와 똑같은 id = 1 인 엔티티를 조회하므로 1차캐쉬에서 가져옴
        Optional<Member> updatedMember = memberRepository.findById(1L);
        assertThat(updatedMember.get().getName()).isEqualTo("Dan"); // true
    }

    @Sql("/members.sql")
    @Test
    public void firstLevelCacheTest2() {
        Optional<Member> member = memberRepository.findById(1L); // member객체가 1차캐쉬에 캐쉬됨
        Member foundMember = member.get();
        foundMember.setName("Dan"); // 1차캐쉬의 member.name = Dan 으로 변경

        entityManager.clear(); // 1차캐쉬 초기화

        // 1차캐쉬가 초기화되어 DB에서 member를 다시조회함
        Optional<Member> updatedMember = memberRepository.findById(1L);
        assertThat(member.get().getName()).isEqualTo("Dan"); // true
        assertThat(updatedMember.get().getName()).isEqualTo("shim"); // true
    }

    @Sql("/members.sql")
    @Test
    public void firstLevelCacheTest3() {
        Optional<Member> member = memberRepository.findById(1L); // member객체가 1차 캐시에 캐시됨
        Member foundMember = member.get();
        foundMember.setName("Dan"); // 1차캐쉬의 member.name = Dan 으로 변경

        entityManager.flush(); // DB에 반영됨 / 1차캐쉬에는 남아있음

        // 1차캐쉬에 id = 1 인 엔티티가 존재 -> 1차캐쉬에서 엔티티를 조회함
        Optional<Member> updatedMember = memberRepository.findById(1L);

        assertThat(member.get().getName()).isEqualTo("Dan"); // true
        assertThat(updatedMember.get().getName()).isEqualTo("Dan"); // true
    }

    @Sql("/members.sql")
    @Test
    public void firstLevelCacheTest4() {
        Optional<Member> member = memberRepository.findById(1L); // member객체가 1차 캐시에 캐시됨
        Member foundMember = member.get();
        foundMember.setName("Dan"); // 1차캐쉬의 member.name = Dan 으로 변경

        entityManager.detach(foundMember); // 영속성 컨텍스트의 관리에서 벗어남 -> 1차캐쉬에서 제거됨

        // 1차캐쉬에 id = 1 인 엔티티가 존재 -> 1차캐쉬에서 엔티티를 조회함
        Optional<Member> updatedMember = memberRepository.findById(1L);

        assertThat(member.get().getName()).isEqualTo("Dan"); // true
        assertThat(updatedMember.get().getName()).isEqualTo("shim"); // true
    }

    @Sql("/members.sql")
    @Test
    public void firstLevelCacheTest5() {
        Optional<Member> member = memberRepository.findById(1L); // member객체가 1차 캐시에 캐시됨
        Member foundMember = member.get();
        foundMember.setName("Dan"); // 1차캐쉬의 member.name = Dan 으로 변경

        entityManager.remove(foundMember); // 1차캐쉬에서 제거 -> flush() 또는 트랜잭션 종료시 DB에서도 제거가 확정됨

        // 1차캐쉬에 엔티티가 없음 -> DB에서 엔티티를 조회
        Optional<Member> removedMember = memberRepository.findById(1L);
        assertThat(removedMember.isPresent()).isEqualTo(false); // 삭제되었음
    }

    @AfterEach
    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
