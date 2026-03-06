package dom.cifra.repo

import dom.cifra.model.User

interface UserRepository {
    suspend fun exists(id: Int): Boolean
    suspend fun findById(id: Int): User?
    suspend fun findAll(): List<User>
    suspend fun findByUsername(username: String): User?
    suspend fun save(user: User): User
}
