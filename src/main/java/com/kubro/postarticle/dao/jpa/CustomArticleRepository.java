package com.kubro.postarticle.dao.jpa;

import java.util.List;

import com.kubro.postarticle.entity.Article;

public interface CustomArticleRepository {
    
  List<Article> findArticleLimitedToAndOffsetBy(Integer limit, Integer offset);
  
  List<Article> findArticleLimitedToAndOffsetByWithStatus(Integer limit, Integer offset, String status);

}
