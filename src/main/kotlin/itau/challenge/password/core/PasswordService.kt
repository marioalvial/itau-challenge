package itau.challenge.password.core

import itau.challenge.password.core.domain.PasswordValidator
import itau.challenge.password.core.domain.Violation
import org.slf4j.LoggerFactory

class PasswordService {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun validate(password: String): List<Violation> {
        val violations = PasswordValidator.validate(password)

        logger.info("Password is valid: ${violations.isEmpty()}")

        return violations
    }
}
