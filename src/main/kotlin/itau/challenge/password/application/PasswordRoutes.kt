package itau.challenge.password.application

import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.password(controller: PasswordController) {
    post("/password/validation") { controller.validate(call) }
}
