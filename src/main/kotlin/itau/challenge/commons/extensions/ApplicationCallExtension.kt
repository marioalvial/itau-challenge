package itau.challenge.commons.extensions

import io.ktor.application.ApplicationCall
import io.ktor.request.receive

suspend fun ApplicationCall.receiveUTF8Text() = String(this.receive(), Charsets.UTF_8)
