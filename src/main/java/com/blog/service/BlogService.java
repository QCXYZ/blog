package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.Type;
import com.blog.exception.NotFoundException;
import com.blog.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
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

    public Blog save(Blog blog) {
        if (blog.getType() == null) {
            blog.setType(new Type(0L, "未分类"));
        }

        return blogRepository.save(blog);
    }

    public Page<Blog> searchBlogs(String query, Pageable pageable) {
        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query != null && !query.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + query + "%"),
                        criteriaBuilder.like(root.get("content"), "%" + query + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    public void deleteBlog(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("博客不存在"));
        blogRepository.delete(blog);
    }
}
