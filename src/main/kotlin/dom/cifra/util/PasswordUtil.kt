package dom.cifra.util;

//Todo implement
class PasswordUtil {
    companion object {
        fun hash(password: String): String = password
        fun compare(password: String, hash: String): Boolean = password == hash
    }
}
