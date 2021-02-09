package itau.challenge.utils.extensions

import com.github.kittinunf.fuel.core.Request
import io.ktor.http.HttpStatusCode

fun Request.getResponse(): Pair<HttpStatusCode, String> {
    val (_, response, _) = this.responseString()

    return HttpStatusCode.fromValue(response.statusCode) to String(response.data, Charsets.UTF_8)
}
