package dom.cifra.model

import dom.cifra.dto.UserDTO

data class User(
    override val id: Int,
    val username: String,
    val role: String,
    val password: String? = null,
    val status: String? = null,
    val contact: Contact? = null
) : Audit<Int>() {

    fun toDTO(token: String? = null): UserDTO {
        return UserDTO(
            token = token,
            id = this.id,
            username = this.username,
            role = this.role,
            contact = contact?.toDTO()
        )
    }

}

