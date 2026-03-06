package dom.cifra.routing

import dom.cifra.service.AuthService
import dom.cifra.constants.Routes
import dom.cifra.request.LoginRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.CannotTransformContentToTypeException
import io.ktor.server.request.receive

import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.example.dom.cifra.request.RegisterRequest
import org.koin.ktor.ext.inject


fun Route.authRouting() {

    val authService by inject<AuthService>()

    route(Routes.Auth.LOGIN) {
        post {
            try {
                val request = call.receive<LoginRequest>()
                call.respond(authService.login(request.username, request.password))
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CannotTransformContentToTypeException || e is BadRequestException)
                    call.respond(HttpStatusCode.BadRequest, "Invalid request body.")
                else
                    call.respond(HttpStatusCode.BadRequest, "Invalid username or password.")
            }
        }
    }

    route(Routes.Auth.REGISTER) {
        post {
            try {
                val request = call.receive<RegisterRequest>()
                call.respond(authService.register(request))
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CannotTransformContentToTypeException || e is BadRequestException)
                    call.respond(HttpStatusCode.BadRequest, "Invalid request body.")
                else
                    call.respond(HttpStatusCode.BadRequest, "Unable to complete registration.")
            }
        }
    }

}
