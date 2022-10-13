package com.ll.exam.security_JWT_exam.app.article.repository;

import com.ll.exam.security_JWT_exam.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}