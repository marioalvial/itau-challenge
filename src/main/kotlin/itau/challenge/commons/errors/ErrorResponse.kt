package itau.challenge.commons.errors

data class ErrorResponse(
    val code: String,
    val message: String,
    val details: MutableMap<String, Any>
) {

    fun addDetail(key: String, value: Any) = this.apply { details[key] = value }

    companion object {
        fun create(message: String, code: String) = ErrorResponse(
            code = code,
            message = message,
            details = mutableMapOf()
        )
    }
}
