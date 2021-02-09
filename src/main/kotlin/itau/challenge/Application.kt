package itau.challenge

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.jackson.JacksonConverter
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import itau.challenge.config.ExceptionHandler
import itau.challenge.config.configModule
import itau.challenge.password.application.partnerModule
import itau.challenge.password.application.password
import org.koin.core.logger.PrintLogger
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get

fun main() {
    val port = (System.getenv("PORT") ?: "8080").toInt()

    ChallengeApplication.start(port)
}

object ChallengeApplication {

    fun start(port: Int): NettyApplicationEngine = embeddedServer(
        factory = Netty,
        watchPaths = listOf("src/main/kotlin/itau.challenge"),
        port = port,
        module = Application::mainModule
    ).start(false)
}

fun Application.mainModule() {
    install(Koin) {
        val modules = listOf(
            configModule,
            partnerModule
        )

        modules(modules)
        logger(PrintLogger())
    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(get()))
    }

    install(StatusPages) { ExceptionHandler.handle(this) }

    routing {
        password(get())
    }
}
