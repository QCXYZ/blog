package com.blog.controller;

import com.blog.entity.Tag;
import com.blog.exception.CustomException;
import com.blog.service.TagService;
import com.blog.util.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class TagController {
    @Resource
    private TagService tagService;

    @PostMapping("/tags")
    public R<Tag> addOrUpdateTag(@RequestBody @Valid Tag tag) {
        try {
            Tag savedTag = tagService.addOrUpdateTag(tag);
            return R.ok(savedTag);
        } catch (CustomException e) {
            return R.fail(400, e.getMessage());
        }
    }
}
