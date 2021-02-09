package itau.challenge.commons.extensions

import itau.challenge.commons.errors.ErrorObject
import itau.challenge.commons.errors.exceptions.MethodArgumentNotValidException
import javax.validation.Validator

fun Validator.executeValidation(validationObject: Any) {
    val violations = validate(validationObject)

    require(violations.isEmpty()) {
        val errors = violations.map {
            val field = it.propertyPath.toString().camelToSnakeCase().toLowerCase()

            ErrorObject(it.message, field, it.invalidValue)
        }

        throw MethodArgumentNotValidException(errors)
    }
}
