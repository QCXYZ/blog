package com.blog.service;

import com.blog.entity.Tag;
import com.blog.exception.CustomException;
import com.blog.repository.TagRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagService {
    @Resource
    private TagRepository tagRepository;

    public Tag addOrUpdateTag(Tag tag) throws CustomException {
        if (tag.getName() != null && tagRepository.existsByName(tag.getName())) {
            throw new CustomException("标签名已存在");
        }
        return tagRepository.save(tag);
    }
}
