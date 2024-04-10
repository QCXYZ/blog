package com.blog.controller;

import com.blog.dto.BlogListRequest;
import com.blog.dto.BlogVO;
import com.blog.entity.Blog;
import com.blog.exception.NotFoundException;
import com.blog.service.BlogService;
import com.blog.util.R;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private ModelMapper modelMapper;

    @PostMapping("/getBlogList")
    public R<?> getBlogList(@RequestBody BlogListRequest request) {
        Page<Blog> page = blogService.listBlogs(request.getPagenum(), request.getPagesize(), request.getTypeId(), request.getTitle());
        if (page != null) {
            // 使用ModelMapper转换
//            List<BlogVO> blogVOList = page.getContent().stream()
//                    .map(blog -> modelMapper.map(blog, BlogVO.class))
//                    .collect(Collectors.toList());
            // 使用BlogVO.withoutContent方法转换
            List<BlogVO> blogVOList = page.getContent().stream()
                    .map(BlogVO::withoutContent)
                    .collect(Collectors.toList());
            return R.ok(blogVOList);
        } else {
            throw new NotFoundException("未找到博客列表");
        }
    }

    @PostMapping("/blogs")
    public R<?> saveOrUpdateBlog(@RequestBody Blog blog) {
        /*Blog savedBlog = */
        blogService.saveOrUpdateBlog(blog);
//        BlogVO blogVO = modelMapper.map(savedBlog, BlogVO.class);
        return R.ok(null);
    }

}
