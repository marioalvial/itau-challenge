package itau.challenge.password.application.requests

import com.fasterxml.jackson.annotation.JsonAutoDetect
import javax.validation.constraints.NotNull

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class ValidatePasswordRequest(
    @field:NotNull(message = "{field.not.null}")
    private val password: String?,
) {

    fun toPassword() = password!!
}
