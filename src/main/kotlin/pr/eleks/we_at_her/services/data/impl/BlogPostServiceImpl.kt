package pr.eleks.we_at_her.services.data.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.stereotype.Service
import pr.eleks.we_at_her.dto.BlogPostDto
import pr.eleks.we_at_her.entities.BlogPost
import pr.eleks.we_at_her.repositories.BlogPostRepository
import pr.eleks.we_at_her.services.data.BlogPostService
import java.util.*
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class BlogPostServiceImpl(
        private val blogPostRepository: BlogPostRepository,
        private val mapper: ObjectMapper
) : BlogPostService {
    override fun addBlogPost(blogPostDto: BlogPostDto) = convertToDto(blogPostRepository.save(convertToEntity(blogPostDto)))

    override fun findAllBlogPosts(): List<BlogPostDto?> =
            StreamSupport.stream(blogPostRepository.findAll().spliterator(), false)
                    .map { blogPost: BlogPost -> convertToDto(blogPost) }
                    .collect(Collectors.toList())
                    .reversed()

    override fun convertToDto(blogPost: BlogPost): BlogPostDto = mapper.convertValue(blogPost)
    override fun convertToEntity(blogPostDto: BlogPostDto): BlogPost = mapper.convertValue(blogPostDto)

}