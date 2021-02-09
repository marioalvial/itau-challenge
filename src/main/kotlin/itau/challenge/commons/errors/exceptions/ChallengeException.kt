package itau.challenge.commons.errors.exceptions

import io.ktor.http.HttpStatusCode
import itau.challenge.commons.errors.ErrorResponse
import itau.challenge.commons.extensions.camelToSnakeCase

abstract class ChallengeException : RuntimeException() {

    protected val code = javaClass
        .simpleName
        .replace("Exception", "")
        .camelToSnakeCase()
        .toUpperCase()

    abstract override val message: String

    abstract fun response(): ErrorResponse

    abstract fun statusCode(): HttpStatusCode
}
