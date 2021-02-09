package itau.challenge.config

import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import itau.challenge.commons.errors.ErrorResponse
import itau.challenge.commons.errors.exceptions.ChallengeException
import org.slf4j.LoggerFactory
import java.lang.Exception

object ExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun handle(handler: StatusPages.Configuration) {
        handleChallengeException(handler)
        handleGenericException(handler)
    }

    private fun handleChallengeException(handler: StatusPages.Configuration) = handler
        .exception<ChallengeException> {
            val error = it.response()

            logger.error("Application throws exception. Code: ${error.code} - Details: ${error.details}")

            call.respond(it.statusCode(), it.response())
        }

    private fun handleGenericException(handler: StatusPages.Configuration) = handler
        .exception<Exception> {
            val error = ErrorResponse.create(it.message ?: "", "GENERAL_ERROR")

            logger.error("Application throws unknown exception. Message: ${it.message}")

            call.respond(HttpStatusCode.InternalServerError, error)
        }
}
