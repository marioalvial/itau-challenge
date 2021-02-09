package itau.challenge.commons.errors

data class ErrorObject(
    val message: String,
    val field: String,
    val invalidParameter: Any?
)
