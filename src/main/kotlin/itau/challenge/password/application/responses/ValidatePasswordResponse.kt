package itau.challenge.password.application.responses

import com.fasterxml.jackson.annotation.JsonAutoDetect
import itau.challenge.password.core.domain.Violation

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class ValidatePasswordResponse(
    private val isValid: Boolean,
    private val errors: List<String>
) {

    companion object {
        fun create(violations: List<Violation>): ValidatePasswordResponse {
            val isValid = violations.isEmpty()
            val errors = violations.map { it.message }

            return ValidatePasswordResponse(
                isValid = isValid,
                errors = errors
            )
        }
    }
}
