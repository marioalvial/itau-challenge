package itau.challenge.password.application

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import itau.challenge.commons.extensions.executeValidation
import itau.challenge.commons.extensions.receiveUTF8Text
import itau.challenge.password.application.requests.ValidatePasswordRequest
import itau.challenge.password.application.responses.ValidatePasswordResponse
import itau.challenge.password.core.PasswordService
import org.slf4j.LoggerFactory
import javax.validation.Validator

class PasswordController(
    private val passwordService: PasswordService,
    private val mapper: ObjectMapper,
    private val validator: Validator
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun validate(call: ApplicationCall) {
        logger.info("Receiving request to validate password")

        val response = call
            .receiveUTF8Text()
            .let { mapper.readValue<ValidatePasswordRequest>(it) }
            .also { validator.executeValidation(it) }
            .let { passwordService.validate(it.toPassword()) }
            .let { ValidatePasswordResponse.create(it) }

        logger.info("Request to validate password was processed with success")

        call.respond(HttpStatusCode.OK, response)
    }
}
