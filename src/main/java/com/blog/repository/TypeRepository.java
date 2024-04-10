package com.blog.repository;

import com.blog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    // 可以根据需要添加更多查询方法，例如根据名称查找分类
    Optional<Type> findByName(String name);
}
