package com.kubro.postarticle.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kubro.postarticle.entity.Article;

@Repository
public class ArticleRepositoryImpl implements CustomArticleRepository {
    
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Article> findArticleLimitedToAndOffsetBy(Integer limit, Integer offset) {
      return entityManager.createQuery("SELECT p FROM Article p",
        Article.class).setMaxResults(limit).setFirstResult(offset).getResultList();
  }

  @Override
  public List<Article> findArticleLimitedToAndOffsetByWithStatus(Integer limit, Integer offset, String status) {
      return entityManager.createQuery("SELECT p FROM Article p WHERE status = :status",
        Article.class)
            .setParameter("status", status)
            .setMaxResults(limit).setFirstResult(offset).getResultList();
  }

}
