package com.kubro.postarticle.api.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kubro.postarticle.entity.Article;
import com.kubro.postarticle.exception.DataFormatException;
import com.kubro.postarticle.mapstruct.dto.ArticleDto;
import com.kubro.postarticle.mapstruct.mapper.MapStructMapper;
import com.kubro.postarticle.service.ArticleService;
import com.kubro.postarticle.type.ArticleStatusType;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/article")
public class ArticleController extends AbstractRestHandler {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MapStructMapper mapStructMapper;
    
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
                "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody Article article,
                HttpServletRequest request, HttpServletResponse response) {
        validate(article);
        Article createdArticle = this.articleService.createArticle(article);
        response.setHeader("Location",
        request.getRequestURL().append("/").append(createdArticle.getId()).toString());
    }

    @RequestMapping(value = "/{limit}/{offset}", method = RequestMethod.GET, produces = { "application/json",
                "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ArticleDto> getAllArticle(
                @PathVariable("limit") Integer limit,
                @PathVariable("offset") Integer offset,
                @RequestParam(required = false) String status,
                HttpServletRequest request, HttpServletResponse response) {
        if (status != null && !status.isEmpty()) {
            return mapStructMapper.articleToArticleDto(this.articleService.getAllArticleByStatus(limit, offset, status));
        }
        return mapStructMapper.articleToArticleDto(this.articleService.getAllArticle(limit, offset));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = { "application/json",
                "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Long countArticle(
                @RequestParam(required = false) String status,
                HttpServletRequest request, HttpServletResponse response) {
        if (status != null && !status.isEmpty()) {
            return this.articleService.countArticleByStatus(status);
        }
        return this.articleService.countArticle();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json",
                "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArticleDto getArticle(
                @PathVariable("id") Integer id,
                HttpServletRequest request, HttpServletResponse response) throws Exception {
        Article article = this.articleService.getArticle(id);
        checkResourceFound(article);
        return mapStructMapper.articleToArticleDto(article);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json",
                "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArticle(
                @PathVariable("id") Integer id, @RequestBody Article article,
                HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.articleService.getArticle(id));
        if (id != article.getId())
                throw new DataFormatException("ID doesn't match!");
        validate(article);
        this.articleService.updateArticle(article);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json",
                "application/xml" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(
                @PathVariable("id") Integer id, HttpServletRequest request,
                HttpServletResponse response) {
        checkResourceFound(this.articleService.getArticle(id));
        this.articleService.deleteArticle(id);
    }
    
    private void validate(Article article) {
        if (article.getTitle() == null || article.getTitle().isEmpty()) {
            throw new DataFormatException("Title is required");
        }
        if (article.getTitle().length() < 20) {
            throw new DataFormatException("Title has minimum 20 characters");
        }
        if (article.getCategory() == null || article.getCategory().isEmpty()) {
            throw new DataFormatException("Category is required");
        }
        if (article.getCategory().length() < 3) {
            throw new DataFormatException("Category has minimum 3 characters");
        }
        if (article.getContent() == null || article.getContent().isEmpty()) {
            throw new DataFormatException("Content is required");
        }
        if (article.getContent().length() < 200) {
            throw new DataFormatException("Content has minimum 200 characters");
        }
        if (article.getStatus() == null || article.getStatus().isEmpty()) {
            throw new DataFormatException("Status is required");
        }
        try {
            ArticleStatusType.fromString(article.getStatus());
        } catch (IllegalArgumentException e) {
            throw new DataFormatException("Status value can only be publish, draft, or thrash");
        }
    }

}
