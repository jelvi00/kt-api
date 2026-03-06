package dom.cifra.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.ApplicationConfig
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }

class DatabaseFactory(private val config: ApplicationConfig) {

    fun init() {

        Database.connect(
            HikariDataSource(
                HikariConfig().apply {
                    driverClassName = config.property("database.driver").getString()
                    jdbcUrl = config.property("database.url").getString()
                    username = config.property("database.user").getString()
                    password = config.property("database.password").getString()
                    maximumPoolSize = 3
                    isAutoCommit = false
                    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                    validate()
                }
            )
        )
    }
}
