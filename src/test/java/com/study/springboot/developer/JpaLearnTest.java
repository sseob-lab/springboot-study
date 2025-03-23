package com.study.springboot.developer;

import com.study.springboot.developer.article.domain.entity.Article;
import com.study.springboot.developer.article.domain.repositories.ArticleRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaLearnTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EntityManager entityManager;

    @Sql("/article.sql")
    @Test
    public void selectTest() {
        Optional<Article> article = articleRepository.findById(1L);

        assertThat(article.isPresent()).isEqualTo(true);
        assertThat(article.get().getId()).isEqualTo(1L);
        assertThat(article.get().getAuthor()).isEqualTo("shim");
    }

    @Sql("/article.sql")
    @Test
    public void selectAllTest() {
        List<Article> article = articleRepository.findAll();

        assertThat(article.get(0).getAuthor()).isEqualTo("shim");
        assertThat(article.get(1).getAuthor()).isEqualTo("yosub");
        assertThat(article.get(2).getAuthor()).isEqualTo("hyonsub");
    }

    @Test
    public void insertTest() {
        Article article = new Article(1L, "title1", "content1", "hyon", now(), now());
        articleRepository.save(article);
        Article savedArticle = articleRepository.findById(1L).orElseThrow();
        assertThat(savedArticle.getId()).isEqualTo(1L);
        assertThat(savedArticle.getAuthor()).isEqualTo("hyon");
    }

    @Test
    public void insertAllTest() {
        List<Article> article = List.of(
                new Article(1L, "title1", "content1", "hyon", now(), now()),
                new Article(2L, "title2", "content2", "sub", now(), now())
        );

        articleRepository.saveAll(article);

        Long count = articleRepository.count();

        assertThat(count).isEqualTo(2);
    }

    @Sql("/article.sql")
    @Test
    public void firstLevelCacheTest1() {
        Optional<Article> article = articleRepository.findById(1L); // article객체가 1차캐쉬에 캐쉬됨
        Article foundArticle = article.get();
        foundArticle.setAuthor("Dan"); // 1차캐쉬의 article.name = Dan 으로 변경

        // 위와 똑같은 id = 1 인 엔티티를 조회하므로 1차캐쉬에서 가져옴
        Optional<Article> updatedArticle = articleRepository.findById(1L);
        assertThat(updatedArticle.get().getAuthor()).isEqualTo("Dan"); // true
    }

    @Sql("/article.sql")
    @Test
    public void firstLevelCacheTest2() {
        Optional<Article> article = articleRepository.findById(1L); // article객체가 1차캐쉬에 캐쉬됨
        Article foundArticle = article.get();
        foundArticle.setAuthor("Dan"); // 1차캐쉬의 article.name = Dan 으로 변경

        entityManager.clear(); // 1차캐쉬 초기화

        // 1차캐쉬가 초기화되어 DB에서 article를 다시조회함
        Optional<Article> updatedArticle = articleRepository.findById(1L);
        assertThat(article.get().getAuthor()).isEqualTo("Dan"); // true
        assertThat(updatedArticle.get().getAuthor()).isEqualTo("shim"); // true
    }

    @Sql("/article.sql")
    @Test
    public void firstLevelCacheTest3() {
        Optional<Article> article = articleRepository.findById(1L); // article객체가 1차 캐시에 캐시됨
        Article foundArticle = article.get();
        foundArticle.setAuthor("Dan"); // 1차캐쉬의 article.name = Dan 으로 변경

        entityManager.flush(); // DB에 반영됨 / 1차캐쉬에는 남아있음

        // 1차캐쉬에 id = 1 인 엔티티가 존재 -> 1차캐쉬에서 엔티티를 조회함
        Optional<Article> updatedArticle = articleRepository.findById(1L);

        assertThat(article.get().getAuthor()).isEqualTo("Dan"); // true
        assertThat(updatedArticle.get().getAuthor()).isEqualTo("Dan"); // true
    }

    @Sql("/article.sql")
    @Test
    public void firstLevelCacheTest4() {
        Optional<Article> article = articleRepository.findById(1L); // article객체가 1차 캐시에 캐시됨
        Article foundArticle = article.get();
        foundArticle.setAuthor("Dan"); // 1차캐쉬의 article.name = Dan 으로 변경

        entityManager.detach(foundArticle); // 영속성 컨텍스트의 관리에서 벗어남 -> 1차캐쉬에서 제거됨

        // 1차캐쉬에 id = 1 인 엔티티가 존재 -> 1차캐쉬에서 엔티티를 조회함
        Optional<Article> updatedArticle = articleRepository.findById(1L);

        assertThat(article.get().getAuthor()).isEqualTo("Dan"); // true
        assertThat(updatedArticle.get().getAuthor()).isEqualTo("shim"); // true
    }

    @Sql("/article.sql")
    @Test
    public void firstLevelCacheTest5() {
        Optional<Article> article = articleRepository.findById(1L); // article객체가 1차 캐시에 캐시됨
        Article foundArticle = article.get();
        foundArticle.setAuthor("Dan"); // 1차캐쉬의 article.name = Dan 으로 변경

        entityManager.remove(foundArticle); // 1차캐쉬에서 제거 -> flush() 또는 트랜잭션 종료시 DB에서도 제거가 확정됨

        // 1차캐쉬에 엔티티가 없음 -> DB에서 엔티티를 조회
        Optional<Article> removedArticle = articleRepository.findById(1L);
        assertThat(removedArticle.isPresent()).isEqualTo(false); // 삭제되었음
    }

    @Sql("/article.sql")
    @Test
    public void firstLevelCacheTest6() {
        Optional<Article> article1 = articleRepository.findById(1L); // article객체가 1차 캐시에 캐시됨
        Optional<Article> article2 = articleRepository.findById(1L); // article객체가 1차 캐시에서 엔티티를가져옴

        assertThat(article1).isEqualTo(article2);
        assertThat(article1.hashCode()).isEqualTo(article2.hashCode());
    }

    @AfterEach
    public void deleteAll() {
        articleRepository.deleteAll();
    }
}
