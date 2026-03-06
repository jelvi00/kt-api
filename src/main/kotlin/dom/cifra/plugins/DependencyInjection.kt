package dom.cifra.plugins

import dom.cifra.database.DatabaseFactory
import dom.cifra.repo.UserRepository
import dom.cifra.repo.UserRepositoryImpl
import dom.cifra.security.PasetoManager
import dom.cifra.service.AuthService
import dom.cifra.service.UserService
import io.ktor.server.config.ApplicationConfig
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

fun makeAppModule(config: ApplicationConfig) = module {

    single { config }

    single { DatabaseFactory(get()).also { it.init() } }.withOptions { createdAtStart() }

    single { PasetoManager(get()) }

    single<UserRepository> { UserRepositoryImpl() }
    single { UserService(get()) }
    single { AuthService(get(), get()) }

}
