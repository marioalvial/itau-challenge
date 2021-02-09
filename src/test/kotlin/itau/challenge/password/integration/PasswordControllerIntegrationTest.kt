package itau.challenge.password.integration

import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import io.ktor.http.HttpStatusCode
import itau.challenge.utils.IntegrationTestExtension
import itau.challenge.utils.extensions.getResponse
import itau.challenge.utils.readJsonResource
import net.javacrumbs.jsonunit.assertj.assertThatJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.test.KoinTest

@ExtendWith(IntegrationTestExtension::class)
class PasswordControllerIntegrationTest : KoinTest {

    private val baseUrl = "http://localhost:8081"

    @Test
    fun `given valid json request should validate password with success`() {
        val requestBody = readJsonResource("password/valid_validate_password_request")
        val expectedResponseBody = readJsonResource("password/valid_validate_password_response")

        val (statusCode, responseBody) = "$baseUrl/password/validation"
            .httpPost()
            .jsonBody(requestBody)
            .getResponse()

        println(responseBody)

        assertThat(statusCode).isEqualTo(HttpStatusCode.OK)
        assertThatJson(responseBody).isEqualTo(expectedResponseBody)
    }

    @Test
    fun `given invalid json request should throw MethodArgumentNotValidException`() {
        val requestBody = readJsonResource("password/invalid_validate_password_request")
        val expectedResponseBody = readJsonResource("password/invalid_validate_password_response")

        val (statusCode, responseBody) = "$baseUrl/password/validation"
            .httpPost()
            .jsonBody(requestBody)
            .getResponse()

        println(responseBody)

        assertThat(statusCode).isEqualTo(HttpStatusCode.BadRequest)
        assertThatJson(responseBody).isEqualTo(expectedResponseBody)
    }
}
