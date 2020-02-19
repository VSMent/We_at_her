package pr.eleks.we_at_her.services.data;

import pr.eleks.we_at_her.dto.BlogPostDto;
import pr.eleks.we_at_her.entities.BlogPost;

public interface BlogPostService {
    BlogPostDto convertToDto(BlogPost blogPost);

    BlogPost convertToEntity(BlogPostDto blogPostDto);

    void addBlogPost(BlogPostDto blogPostDto);
}
