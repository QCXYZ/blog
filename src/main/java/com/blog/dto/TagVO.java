package com.blog.dto;

import com.blog.entity.Tag;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TagVO {
    private Long id;
    private String name;

    public static Set<TagVO> from(Set<Tag> tags) {
        return tags.stream().map(tag -> {
            TagVO tagVO = new TagVO();
            tagVO.setId(tag.getId());
            tagVO.setName(tag.getName());
            return tagVO;
        }).collect(Collectors.toSet());
    }
}
