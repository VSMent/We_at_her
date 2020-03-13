package pr.eleks.we_at_her.controllers

import org.springframework.web.bind.annotation.*
import pr.eleks.we_at_her.dto.BlogPostDto
import pr.eleks.we_at_her.services.data.BlogPostService

@RestController
@RequestMapping("/REST")
class BlogPostController(
        private val blogPostService: BlogPostService
) {
    @PostMapping("/blogPost")
    fun addBlogPost(@RequestBody blogPostDto: BlogPostDto) = blogPostService.addBlogPost(blogPostDto)

    @GetMapping("/blogPost")
    fun getBlogPosts(): List<BlogPostDto?>? = blogPostService.findAllBlogPosts()
}