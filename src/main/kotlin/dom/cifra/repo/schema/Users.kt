package dom.cifra.repo.schema

import org.jetbrains.exposed.sql.Table

object Users: Table("users") {
    val id = integer("id").autoIncrement()
    val username = text("username")
    val role = text("role")
    val status = text("status")
    val password = text("password")

    override val primaryKey = PrimaryKey(id)
}
