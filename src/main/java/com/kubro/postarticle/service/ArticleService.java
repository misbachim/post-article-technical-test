package com.kubro.postarticle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubro.postarticle.dao.jpa.ArticleRepository;
import com.kubro.postarticle.entity.Article;

@Service
public class ArticleService {

    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleService() {
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article getArticle(Integer id) {
        return articleRepository.findById(id).get();
    }

    public void updateArticle(Article article) {
        Article currentArticle = getArticle(article.getId());
        article.setCreatedDate(currentArticle.getCreatedDate());

        articleRepository.save(article);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

    public List<Article> getAllArticle(Integer offset, Integer limit) {
        List<Article> articles = articleRepository.findArticleLimitedToAndOffsetBy(offset, limit);
        return articles;
    }

    public List<Article> getAllArticleByStatus(Integer offset, Integer limit, String status) {
        List<Article> articles = articleRepository.findArticleLimitedToAndOffsetByWithStatus(offset, limit, status);
        return articles;
    }
    
    public Long countArticle() {
        Long articles = articleRepository.count();
        return articles;
    }
    
    public Long countArticleByStatus(String status) {
        Long articles = articleRepository.countByStatus(status);
        return articles;
    }
    
}
