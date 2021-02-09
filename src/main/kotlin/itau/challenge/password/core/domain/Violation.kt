package itau.challenge.password.core.domain

enum class Violation(val message: String) {

    PASSWORD_LENGTH("Password length must have at least 9 characters"),
    MISSING_DIGIT("Password must contain at least one digit"),
    MISSING_LOWER_CASE("Password must contain at least one lower case character"),
    MISSING_UPPER_CASE("Password must contain at least one digit"),
    MISSING_SPECIAL_CHARACTER("Password must contain at least one special character"),
    HAS_WHITESPACE("Password must not have whitespace"),
    REPEATED_CHARACTER("Password cannot contain repeated characters"),
}
