package pr.eleks.we_at_her.repositories

import org.springframework.data.repository.CrudRepository
import pr.eleks.we_at_her.entities.BlogPost

interface BlogPostRepository : CrudRepository<BlogPost, Long>