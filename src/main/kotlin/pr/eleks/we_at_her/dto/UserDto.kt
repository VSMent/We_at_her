package pr.eleks.we_at_her.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDto(
        var id: Long?,
        var username: String?,
        var password: String?,
        var cityId: Long?,
        var email: String?,
        var role: RoleDto?,
        var activated: Boolean = false,
        var uuid: UUID?
)