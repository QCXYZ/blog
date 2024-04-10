package com.blog.dto;

import com.blog.entity.Type;
import lombok.Data;

@Data
public class TypeVO {
    private Long id;
    private String name;

    public static TypeVO from(Type type) {
        TypeVO typeVO = new TypeVO();
        typeVO.setId(type.getId());
        typeVO.setName(type.getName());
        return typeVO;
    }
}
