package pr.eleks.we_at_her.services.data.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pr.eleks.we_at_her.dto.CityDto
import pr.eleks.we_at_her.dto.RoleDto
import pr.eleks.we_at_her.dto.UserDto
import pr.eleks.we_at_her.entities.City
import pr.eleks.we_at_her.entities.User
import pr.eleks.we_at_her.exceptions.UUIDNotFoundException
import pr.eleks.we_at_her.repositories.UserRepository
import pr.eleks.we_at_her.services.data.UserService
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class UserSecriveImpl(
        private val userRepository: UserRepository,
        private val mapper: ObjectMapper,
        private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun createUser(userDto: UserDto): UserDto =
            userDto
                    .apply { role = RoleDto.USER }
                    .apply { password = passwordEncoder.encode(password) }
                    .apply { uuid = UUID.nameUUIDFromBytes((username+email).toByteArray(StandardCharsets.UTF_8)) }
                    .let { convertToEntity(it) }
                    .let { userRepository.save(it) }
                    .let { convertToDto(it) }

    @Throws(UUIDNotFoundException::class)
    override fun findUserByUuid(uuidString: String): UserDto =
            userRepository.findByUuid(UUID.fromString(uuidString))
                    ?.let { convertToDto(it) }
                    ?: throw UUIDNotFoundException(uuidString)

    override fun activateUserByUuid(uuidString: String): UserDto =
            findUserByUuid(uuidString)
                    .apply { activated = true }
                    .let { convertToEntity(it) }
                    .let { userRepository.save(it) }
                    .let { convertToDto(it) }

    override fun loadUserByUsername(username: String): UserDetails =
            userRepository.findByUsername(username)
                    ?: throw UsernameNotFoundException(username)

    override fun convertToDto(user: User): UserDto = mapper.convertValue(user)
    override fun convertToEntity(userDto: UserDto): User = mapper.convertValue(userDto)
}