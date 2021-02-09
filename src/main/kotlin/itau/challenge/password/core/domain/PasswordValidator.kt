package itau.challenge.password.core.domain

import itau.challenge.password.core.domain.Violation.HAS_WHITESPACE
import itau.challenge.password.core.domain.Violation.MISSING_DIGIT
import itau.challenge.password.core.domain.Violation.MISSING_LOWER_CASE
import itau.challenge.password.core.domain.Violation.MISSING_SPECIAL_CHARACTER
import itau.challenge.password.core.domain.Violation.MISSING_UPPER_CASE
import itau.challenge.password.core.domain.Violation.PASSWORD_LENGTH
import itau.challenge.password.core.domain.Violation.REPEATED_CHARACTER

object PasswordValidator {

    private const val PASSWORD_MIN_LENGTH = 9

    fun validate(password: String): List<Violation> = listOfNotNull(
        verifyPasswordLength(password),
        hasDigit(password),
        hasLowerCase(password),
        hasUpperCase(password),
        hasSpecialCharacter(password),
        hasWhitespace(password),
        hasRepeatedCharacter(password),
    )

    private fun verifyPasswordLength(password: String) =
        if (password.length < PASSWORD_MIN_LENGTH) PASSWORD_LENGTH else null

    private fun hasDigit(password: String) = if (password.contains("\\d".toRegex())) null else MISSING_DIGIT

    private fun hasLowerCase(password: String) = if (password.any { it.isLowerCase() }) null else MISSING_LOWER_CASE

    private fun hasUpperCase(password: String) = if (password.any { it.isUpperCase() }) null else MISSING_UPPER_CASE

    private fun hasSpecialCharacter(password: String) =
        if (password.contains("[!@#\$%^&*()-+]".toRegex())) null else MISSING_SPECIAL_CHARACTER

    private fun hasWhitespace(password: String) = if (password.contains("\\s".toRegex())) HAS_WHITESPACE else null

    private fun hasRepeatedCharacter(password: String): Violation? {
        var previousCharacter: Char? = null
        var result: Violation? = null

        for (character in password.toList().sorted()) {
            if (character == previousCharacter) {
                result = REPEATED_CHARACTER

                break
            } else {
                previousCharacter = character
            }
        }

        return result
    }
}
