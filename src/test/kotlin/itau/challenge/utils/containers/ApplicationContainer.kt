package itau.challenge.utils.containers

import io.ktor.server.netty.NettyApplicationEngine
import itau.challenge.ChallengeApplication

object ApplicationContainer {

    private lateinit var server: NettyApplicationEngine
    private var isRunning = false

    fun start() {
        if (isRunning.not()) {
            println("Starting application...")

            server = ChallengeApplication.start(8081)
            isRunning = true
        } else {
            println("Application wasn't started because it is already running")
        }
    }

    fun stop() {
        if (isRunning) {
            println("Stopping application...")

            server.stop(5, 10)
            isRunning = false
        } else {
            println("Application wasn't stopped because it is not running")
        }
    }
}
