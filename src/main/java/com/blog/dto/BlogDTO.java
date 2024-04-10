package com.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class BlogDTO {
    private Long id; // 可以为空，表示添加新博客
    @NotEmpty(message = "博客标题不能为空")
    private String title;
    private String content;
    private Long typeId; // 类型ID
    private Set<Long> tagIds; // 标签ID集合
}
