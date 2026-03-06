package dom.cifra.constants

object Routes {
    private const val V1 = "/api/v1"

    object Auth {
        const val LOGIN = "$V1/auth/login"
        const val REGISTER = "$V1/auth/registration"
    }

    object User {
        const val MANY = "$V1/users"
        const val DETAIL = "$V1/users/{id}/detail"
    }
}
