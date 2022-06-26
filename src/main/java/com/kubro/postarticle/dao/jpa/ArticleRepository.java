package com.kubro.postarticle.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kubro.postarticle.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>, CustomArticleRepository {
    
  Long countByStatus(String status);

}
