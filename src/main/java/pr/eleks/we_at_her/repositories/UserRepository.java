package pr.eleks.we_at_her.repositories;

import org.springframework.data.repository.CrudRepository;
import pr.eleks.we_at_her.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}