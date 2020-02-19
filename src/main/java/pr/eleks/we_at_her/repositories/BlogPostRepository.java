package pr.eleks.we_at_her.repositories;

import org.springframework.data.repository.CrudRepository;
import pr.eleks.we_at_her.entities.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
}
