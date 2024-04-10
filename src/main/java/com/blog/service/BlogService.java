package com.blog.service;

import com.blog.entity.Blog;
import com.blog.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BlogService {
    @Resource
    private BlogRepository blogRepository;

    public Page<Blog> listBlogs(int pageNum, int pageSize, Long typeId, String title) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        if (typeId != null && title != null) {
            return blogRepository.findByTitleContainingAndTypeId(title, typeId, pageRequest);
        } else if (typeId != null) {
            return blogRepository.findByTypeId(typeId, pageRequest);
        } else if (title != null) {
            return blogRepository.findByTitleContaining(title, pageRequest);
        } else {
            return blogRepository.findAll(pageRequest);
        }
    }

    public void saveOrUpdateBlog(Blog blog) {
        blog.setUpdatedTime(new Date());
        if (blog.getId() == null) {
            blog.setCreatedTime(new Date());
        }
        blogRepository.save(blog);
    }

}
