package pr.eleks.we_at_her.repositories;

import org.springframework.data.repository.CrudRepository;
import pr.eleks.we_at_her.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}