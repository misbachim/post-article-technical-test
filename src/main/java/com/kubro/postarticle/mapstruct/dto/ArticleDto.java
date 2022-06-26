package com.kubro.postarticle.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ArticleDto {

    private String id;

    private String title;

    private String content;

    private String category;

    private String status;

}
