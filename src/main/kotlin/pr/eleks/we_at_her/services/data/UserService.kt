package pr.eleks.we_at_her.services.data

import org.springframework.security.core.userdetails.UserDetailsService
import pr.eleks.we_at_her.dto.UserDto
import pr.eleks.we_at_her.entities.User
import pr.eleks.we_at_her.exceptions.UUIDNotFoundException

interface UserService : UserDetailsService {
    fun createUser(userDto: UserDto): UserDto
    fun activateUserByUuid(uuidString: String): UserDto
    fun findUserByUuid(uuidString: String): UserDto?
    fun convertToDto(user: User): UserDto
    fun convertToEntity(userDto: UserDto): User
}