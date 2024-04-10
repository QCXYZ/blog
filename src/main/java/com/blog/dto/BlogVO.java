package com.blog.dto;

import com.blog.entity.Blog;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BlogVO {
    private Long id;
    private String title;
    private String content;
    private Date createdTime;
    private Date updatedTime;
    private TypeVO type;
    private Set<TagVO> tags;

    public static BlogVO withoutContent(Blog blog) {
        BlogVO blogVO = new BlogVO();
        blogVO.setId(blog.getId());
        blogVO.setTitle(blog.getTitle());
//        blogVO.setContent(blog.getContent());
        blogVO.setCreatedTime(blog.getCreatedTime());
        blogVO.setUpdatedTime(blog.getUpdatedTime());
        blogVO.setType(TypeVO.from(blog.getType()));
        blogVO.setTags(TagVO.from(blog.getTags()));
        return blogVO;
    }
}
