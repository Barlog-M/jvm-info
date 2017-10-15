package app.service

import io.undertow.Undertow
import io.undertow.UndertowOptions
import mu.KLogging
import java.net.InetSocketAddress

object Server : KLogging() {
	private var port: Int = 0

	private val server by lazy {
		Undertow.builder()
			.setServerOption(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false)
			.addHttpListener(port, "0.0.0.0")
			.setHandler(Router.handler).build()
	}

	fun start() = start(0)

	fun start(port: Int): Int {
		this.port = port
		server.start()
		val boundPort = (server.listenerInfo.first().address as InetSocketAddress).port
		logger.info { "Server start on port: $boundPort" }
		return boundPort
	}

	fun stop() {
		server.stop()
		logger.info { "Server stop" }
	}
}
