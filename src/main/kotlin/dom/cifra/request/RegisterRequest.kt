package org.example.dom.cifra.request

import dom.cifra.model.User
import kotlinx.serialization.Serializable

//TODO implement
@Serializable
data class RegisterRequest(var username: String, val role: String, var password: String) {

    fun toUser(): User {

        return User(0, username, role, password)
    }
}
