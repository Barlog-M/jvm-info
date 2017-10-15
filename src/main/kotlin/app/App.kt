package app

import app.service.Server

fun main(vararg args: String) {
	Runtime.getRuntime().addShutdownHook(Thread(Runnable { Server.stop() }))
	Server.start(port(*args))
}
