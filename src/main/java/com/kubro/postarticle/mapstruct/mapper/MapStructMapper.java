package com.kubro.postarticle.mapstruct.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.kubro.postarticle.entity.Article;
import com.kubro.postarticle.mapstruct.dto.ArticleDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MapStructMapper {
    ArticleDto articleToArticleDto(Article article);
    List<ArticleDto> articleToArticleDto(List<Article> article);

}