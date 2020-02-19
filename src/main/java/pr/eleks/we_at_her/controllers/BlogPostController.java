package pr.eleks.we_at_her.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.entities.BlogPost;
import pr.eleks.we_at_her.services.data.BlogPostService;

@RestController
@RequestMapping("/REST")
public class BlogPostController {
    private BlogPostService blogPostService;
    private ObjectMapper mapper;

    public BlogPostController(BlogPostService blogPostService, ObjectMapper mapper) {
        this.blogPostService = blogPostService;
        this.mapper = mapper;
    }

    @PostMapping("/blogPost")
    public void addBlogPost(@RequestBody BlogPostDto blogPostDto) {
        blogPostService.addBlogPost(blogPostDto);
    }

    @GetMapping("/blogPost")
    public String getBlogPosts() {
        return "get";
    }

    public BlogPostDto convertToDto(BlogPost blogPost) {
        if (blogPost == null) {
            return null;
        }
        return mapper.convertValue(blogPost, BlogPostDto.class);
    }

    public BlogPost convertToEntity(BlogPostDto blogPostDto) {
        if (blogPostDto == null) {
            return null;
        }
        return mapper.convertValue(blogPostDto, BlogPost.class);
    }
}
