package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.Type;
import com.blog.repository.BlogRepository;
import com.blog.repository.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TypeService {
    @Resource
    private TypeRepository typeRepository;
    @Resource
    private BlogRepository blogRepository;

    public void saveOrUpdate(Type type) {
        // 校验name是否已存在
//        如果findByName方法没有找到匹配的类型，则返回的Optional对象将是
//        “空的”（Optional.empty()），而不是null
        Optional<Type> existingType = typeRepository.findByName(type.getName());
        if (existingType.isPresent() && !existingType.get().getId().equals(type.getId())) {
            throw new IllegalArgumentException("分类名称已存在");
        }
        typeRepository.save(type);
    }

    public void deleteType(Long typeId) {
        List<Blog> blogs = blogRepository.findByTypeId(typeId);
        if (!blogs.isEmpty()) {
            for (Blog blog : blogs) {
                blog.setType(new Type(0L, "未分类"));
                blogRepository.save(blog);
            }
        }
        typeRepository.deleteById(typeId);
    }

}
