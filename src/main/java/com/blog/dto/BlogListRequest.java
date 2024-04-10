package com.blog.dto;

import lombok.Data;

@Data
public class BlogListRequest {
    private int pagenum;
    private int pagesize;
    private Long typeId;
    private String title;
}
