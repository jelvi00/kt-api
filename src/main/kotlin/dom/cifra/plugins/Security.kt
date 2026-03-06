package dom.cifra.plugins


import dom.cifra.repo.UserRepository
import dom.cifra.security.PasetoManager
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.bearer
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val pasetoManager by inject<PasetoManager>()
    val userRepository by inject<UserRepository>()

    install(Authentication) {
        bearer("paseto-auth") {
            authenticate { tokenCredential ->
                val userId = pasetoManager.getUserId(tokenCredential.token)
                if (userId != null && userRepository.exists(userId.toInt()))
                    UserIdPrincipal(userId) else null
            }

        }
    }
}
