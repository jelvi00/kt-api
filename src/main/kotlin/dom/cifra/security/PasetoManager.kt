package dom.cifra.security

import io.ktor.server.config.ApplicationConfig
import org.paseto4j.commons.*
import org.paseto4j.version4.PasetoLocal
import java.time.Instant

class PasetoManager(config: ApplicationConfig) {

    private val secretKey = SecretKey(config.property("paseto.secret_key").getString().toByteArray(), Version.V4)

    fun createToken(userId: Int): String {
        val payload = """{"sub":$userId, "exp":${Instant.now().plusSeconds(86400)}"}"""
        return PasetoLocal.encrypt(secretKey, payload, "", "")
    }

    fun getUserId(token: String): String? {
        return try {
            PasetoLocal.decrypt(secretKey, token, "").substringAfter("\"sub\":")
                .substringBefore(",")
                .substringBefore("}")
        } catch (e: Exception) {
            null
        }
    }
}
