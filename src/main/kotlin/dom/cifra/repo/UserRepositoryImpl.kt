package dom.cifra.repo

import dom.cifra.model.User
import dom.cifra.database.dbQuery
import dom.cifra.model.Contact
import dom.cifra.repo.schema.Contacts
import dom.cifra.repo.schema.Users
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertReturning
import org.jetbrains.exposed.sql.selectAll

class UserRepositoryImpl: UserRepository {

    override suspend fun exists(id: Int): Boolean = dbQuery {
        Users.selectAll()
            .where { Users.id eq id and(Users.status eq "REGISTERED") }
            .count() > 0
    }

    override suspend fun findAll(): List<User> = dbQuery {
        Users
            .selectAll()
            .where { Users.status eq "REGISTERED" }
            .map {
                User(
                    id = it[Users.id],
                    username = it[Users.username],
                    role = it[Users.role],
                )
            }
    }

    override suspend fun findById(id: Int): User? = dbQuery {
        (Users leftJoin Contacts)
            .selectAll()
            .where { Users.id eq id }
            .map {
                User(
                    id = it[Users.id],
                    username = it[Users.username],
                    role = it[Users.role],
                    status = it[Users.status],
                    contact = it.getOrNull(Contacts.id)?.let { _ ->
                        Contact(
                            id = it[Contacts.id],
                            firstname = it[Contacts.firstname],
                            lastname = it[Contacts.lastname],
                            email = it[Contacts.email]
                        )
                    }
                )
            }
            .singleOrNull()
    }

    override suspend fun findByUsername(username: String): User? = dbQuery {
        Users.select(Users.id, Users.username, Users.role, Users.password)
            .where { Users.username eq username and(Users.status eq "REGISTERED") }
            .map {
                User(
                    id = it[Users.id],
                    username = it[Users.username],
                    role = it[Users.role],
                    password = it[Users.password]
                )
            }
        .singleOrNull()
    }

    override suspend fun save(user: User): User = dbQuery {
        Users.insertReturning {
            it[Users.username] = user.username
            it[Users.role] = user.role
            it[Users.password] = user.password.toString()
            it[Users.status] = "REGISTERED"
        }
            .map {
                User(
                    id = it[Users.id],
                    username = it[Users.username],
                    role = it[Users.role],
                    status = it[Users.status]
                )
            }
            .singleOrNull() ?: throw Exception("User not saved")
    }

}
