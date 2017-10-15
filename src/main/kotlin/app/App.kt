package app

import app.service.Server
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(vararg args: String) {
	logger.info { "args: $args" }

	Runtime.getRuntime().addShutdownHook(Thread(Runnable { Server.stop() }))
	Server.start(port(*args))
}
