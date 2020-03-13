package pr.eleks.we_at_her.repositories

import org.springframework.data.repository.CrudRepository
import pr.eleks.we_at_her.entities.User
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByUuid(userUuid: UUID): User?
}