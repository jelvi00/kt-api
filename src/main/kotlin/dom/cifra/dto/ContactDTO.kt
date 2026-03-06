package dom.cifra.dto

import kotlinx.serialization.Serializable

@Serializable
data class ContactDTO(val id: String, val firstname: String, val lastname: String, val email: String)
