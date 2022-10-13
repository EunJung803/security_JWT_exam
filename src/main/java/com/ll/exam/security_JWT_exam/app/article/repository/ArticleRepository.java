package com.ll.exam.security_JWT_exam.app.article.repository;

import com.ll.exam.security_JWT_exam.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}