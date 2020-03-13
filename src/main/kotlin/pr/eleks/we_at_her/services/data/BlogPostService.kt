package pr.eleks.we_at_her.services.data

import pr.eleks.we_at_her.dto.BlogPostDto
import pr.eleks.we_at_her.entities.BlogPost

interface BlogPostService {
    fun convertToDto(blogPost: BlogPost): BlogPostDto
    fun convertToEntity(blogPostDto: BlogPostDto): BlogPost
    fun addBlogPost(blogPostDto: BlogPostDto): BlogPostDto
    fun findAllBlogPosts(): List<BlogPostDto?>
}