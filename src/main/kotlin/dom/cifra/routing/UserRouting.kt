package dom.cifra.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import dom.cifra.service.UserService
import io.ktor.server.auth.authenticate
import org.koin.ktor.ext.inject


fun Route.userRouting() {

    val userService by inject<UserService>()

    authenticate("paseto-auth") {
        route("/users") {
            get {
                call.respond(userService.getAllUsers())
            }
        }

        route("/users/{id}/detail") {
            get {
                val id = call.parameters["id"]?.toIntOrNull()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid id")
                    return@get
                }

                call.respond(userService.getUser(id))
            }
        }
    }

}
