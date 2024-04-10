package com.blog.controller;

import com.blog.entity.Type;
import com.blog.service.TypeService;
import com.blog.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("admin")
public class TypeController {
    @Resource
    private TypeService typeService;

    @PostMapping("types")
    public R<?> saveOrUpdate(@RequestBody Type type) {
        try {
            typeService.saveOrUpdate(type);
            return R.ok(null);
        } catch (IllegalArgumentException e) {
            return R.fail(400, e.getMessage());
        }
    }

    @GetMapping("/types/{id}/delete")
    public R<?> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return R.ok(null);
    }

}
