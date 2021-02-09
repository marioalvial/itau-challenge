package itau.challenge.commons.errors.exceptions

import io.ktor.http.HttpStatusCode
import itau.challenge.commons.errors.ErrorObject
import itau.challenge.commons.errors.ErrorResponse

class MethodArgumentNotValidException(
    errors: List<ErrorObject>
) : ChallengeException() {

    private val errors = errors.sortedWith(compareBy(ErrorObject::field, ErrorObject::message))

    override val message = "Object contains invalid data"

    override fun response() = ErrorResponse.create(message, code).addDetail("errors", errors)

    override fun statusCode() = HttpStatusCode.BadRequest
}
