package dom.cifra.service

import dom.cifra.dto.UserDTO
import dom.cifra.repo.UserRepository
import dom.cifra.security.PasetoManager
import dom.cifra.util.PasswordUtil
import org.example.dom.cifra.request.RegisterRequest

class AuthService(private val userRepository: UserRepository, private val pasetoManager: PasetoManager) {

    suspend fun login(username: String, password: String): UserDTO {

        val user = userRepository.findByUsername(username.lowercase()) ?: throw Exception("User not found, username: [$username]")

        if (!PasswordUtil.compare(password, user.password.toString()))
            throw Exception("Invalid password, username: [$username]")

        return user.toDTO(pasetoManager.createToken(user.id))

    }

    suspend fun register(request: RegisterRequest): UserDTO {

        request.username = request.username.lowercase()
        request.password = PasswordUtil.hash(request.password)

        val user = userRepository.save(request.toUser())

        return user.toDTO(pasetoManager.createToken(user.id))

    }
}
