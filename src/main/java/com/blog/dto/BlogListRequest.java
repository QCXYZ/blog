package com.blog.dto;

import lombok.Data;

@Data
public class BlogListRequest {
    private int pagenum = 1;
    private int pagesize = 10;
    private Long typeId;
    private String title;
}
