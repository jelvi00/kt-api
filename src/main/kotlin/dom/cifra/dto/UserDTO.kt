package dom.cifra.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val token: String? = null,
    val id: Int,
    val username: String,
    val role: String,
    val status: String? = null,
    val contact: ContactDTO?
) {}
