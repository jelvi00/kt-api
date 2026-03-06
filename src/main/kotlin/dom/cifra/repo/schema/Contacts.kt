package dom.cifra.repo.schema

import org.jetbrains.exposed.sql.Table

object Contacts: Table("contacts") {
    val id = text("id")
    val firstname = text("firstname")
    val lastname = text("lastname")
    val email =  text("email")
    val userId = integer("user_id") references Users.id.uniqueIndex()

    override val primaryKey = PrimaryKey(id)
}
