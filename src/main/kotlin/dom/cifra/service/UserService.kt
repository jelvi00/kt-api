package dom.cifra.service

import dom.cifra.dto.UserDTO
import dom.cifra.repo.UserRepository

class UserService(private val userRepository: UserRepository) {

    suspend fun getAllUsers(): List<UserDTO> {

        val users = userRepository.findAll()

        return users.map { it.toDTO() }

    }

    suspend fun getUser(id: Int): UserDTO {

        val user = userRepository.findById(id) ?: throw Exception("User not found")

        return user.toDTO()

    }
}
