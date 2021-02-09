package itau.challenge.password.unit.core.domain

import itau.challenge.password.core.domain.PasswordValidator
import itau.challenge.password.core.domain.Violation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PasswordValidatorTest {

    @Test
    fun `given empty string should return violations`() {
        val expectedViolations = arrayOf(
            Violation.PASSWORD_LENGTH,
            Violation.MISSING_DIGIT,
            Violation.MISSING_LOWER_CASE,
            Violation.MISSING_UPPER_CASE,
            Violation.MISSING_SPECIAL_CHARACTER
        )

        val violations = PasswordValidator.validate("")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'aa' should return violations`() {
        val expectedViolations = arrayOf(
            Violation.PASSWORD_LENGTH,
            Violation.MISSING_DIGIT,
            Violation.MISSING_UPPER_CASE,
            Violation.MISSING_SPECIAL_CHARACTER,
            Violation.REPEATED_CHARACTER
        )

        val violations = PasswordValidator.validate("aa")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'ab' should return violations`() {
        val expectedViolations = arrayOf(
            Violation.PASSWORD_LENGTH,
            Violation.MISSING_DIGIT,
            Violation.MISSING_UPPER_CASE,
            Violation.MISSING_SPECIAL_CHARACTER
        )

        val violations = PasswordValidator.validate("ab")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'AAAbbbCc' should return violations`() {
        val expectedViolations = arrayOf(
            Violation.PASSWORD_LENGTH,
            Violation.MISSING_DIGIT,
            Violation.MISSING_SPECIAL_CHARACTER,
            Violation.REPEATED_CHARACTER
        )

        val violations = PasswordValidator.validate("AAAbbbCc")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'AbTp9!foo' should return violations`() {
        val expectedViolations = arrayOf(
            Violation.REPEATED_CHARACTER
        )

        val violations = PasswordValidator.validate("AbTp9!foo")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'AbTp9!foA' should return violations`() {
        val expectedViolations = arrayOf(
            Violation.REPEATED_CHARACTER
        )

        val violations = PasswordValidator.validate("AbTp9!foA")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'AbTp9 fok' should return violations`() {
        val expectedViolations = arrayOf(
            Violation.MISSING_SPECIAL_CHARACTER,
            Violation.HAS_WHITESPACE
        )

        val violations = PasswordValidator.validate("AbTp9 fok")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }

    @Test
    fun `given string 'AbTp9!fok' should return empty list`() {
        val expectedViolations = emptyArray<Violation>()

        val violations = PasswordValidator.validate("AbTp9!fok")

        assertThat(violations).containsExactlyInAnyOrder(*expectedViolations)
    }
}
