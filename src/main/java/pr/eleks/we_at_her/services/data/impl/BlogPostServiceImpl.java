package pr.eleks.we_at_her.services.data.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.dto.CityDto;
import pr.eleks.we_at_her.entities.BlogPost;
import pr.eleks.we_at_her.repositories.BlogPostRepository;
import pr.eleks.we_at_her.services.data.BlogPostService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private BlogPostRepository blogPostRepository;
    private ObjectMapper mapper;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, ObjectMapper mapper) {
        this.blogPostRepository = blogPostRepository;
        this.mapper = mapper;
    }

    @Override
    public void addBlogPost(BlogPostDto blogPostDto) {
        blogPostRepository.save(convertToEntity(blogPostDto));
    }

    @Override
    public List<BlogPostDto> findAllBlogPosts() {
        return StreamSupport.stream(blogPostRepository.findAll().spliterator(), false)
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public BlogPostDto convertToDto(BlogPost blogPost) {
        if (blogPost == null) {
            return null;
        }
        return mapper.convertValue(blogPost, BlogPostDto.class);
    }

    @Override
    public BlogPost convertToEntity(BlogPostDto blogPostDto) {
        if (blogPostDto == null) {
            return null;
        }
        return mapper.convertValue(blogPostDto, BlogPost.class);
    }
}
