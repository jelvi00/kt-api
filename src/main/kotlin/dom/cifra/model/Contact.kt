package dom.cifra.model

import dom.cifra.dto.ContactDTO


data class Contact(override val id: String, val firstname: String, val lastname: String, val email: String): Audit<String>() {

    fun toDTO(): ContactDTO {
        return ContactDTO(
            id = this.id,
            firstname = this.firstname,
            lastname = this.lastname,
            email = this.email,
        )
    }
}
