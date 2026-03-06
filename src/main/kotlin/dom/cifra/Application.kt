package dom.cifra

import dom.cifra.plugins.configureSecurity
import dom.cifra.plugins.configureSerialization
import dom.cifra.plugins.configureRouting
import dom.cifra.plugins.makeAppModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(makeAppModule(environment.config))
    }

    configureSecurity()
    configureSerialization()
    configureRouting()
}
