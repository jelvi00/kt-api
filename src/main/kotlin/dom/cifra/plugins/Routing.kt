package dom.cifra.plugins

import dom.cifra.routing.userRouting
import dom.cifra.routing.authRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        userRouting()
        authRouting()
    }
}
