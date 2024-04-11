package com.blog.controller;

import com.blog.dto.BlogDTO;
import com.blog.dto.BlogListRequest;
import com.blog.dto.BlogVO;
import com.blog.entity.Blog;
import com.blog.service.BlogService;
import com.blog.util.R;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin")
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private ModelMapper modelMapper;

    @PostMapping("getBlogList")
    public R<?> getBlogList(@RequestBody BlogListRequest request) {
        Page<Blog> page = blogService.listBlogs(request.getPagenum(), request.getPagesize(), request.getTypeId(), request.getTitle());
        // 使用ModelMapper转换
//            List<BlogVO> blogVOList = page.getContent().stream()
//                    .map(blog -> modelMapper.map(blog, BlogVO.class))
//                    .collect(Collectors.toList());
        // 使用BlogVO.withoutContent方法转换
        List<BlogVO> blogVOList = page.getContent().stream()
                .map(BlogVO::withoutContent)
                .collect(Collectors.toList());
        return R.ok(blogVOList);
    }

    @PostMapping("blogs")
    public R<?> addOrUpdateBlog(@Valid @RequestBody BlogDTO blogDTO) {
        Blog blog = modelMapper.map(blogDTO, Blog.class);

        if (blog.getId() == null) {
            blog.setCreatedTime(new Date());
        }
        blog.setUpdatedTime(new Date());

        Blog savedBlog = blogService.save(blog);
        return R.ok(savedBlog); // 通常无需返回数据, 这里为了方便查看数据
    }

    @GetMapping("search")
    public R<?> searchBlogs(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Blog> blogsPage = blogService.searchBlogs(query, PageRequest.of(page, size));
        // 转换blogsPage为所需的格式，如果有必要 (这里没必要)
        return R.ok(blogsPage.getContent());
    }

    @GetMapping("{id}/delete")
    public R<?> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return R.ok("博客删除成功");
    }

}
