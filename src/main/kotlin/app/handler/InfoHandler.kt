package app.handler

import app.Context
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.Headers
import io.undertow.util.Methods
import app.service.Informer
import mu.KLogging

object InfoHandler : HttpHandler, KLogging() {
	private val mapper = Context.objectMapper

	override fun handleRequest(exchange: HttpServerExchange) {
		logger.info { "request method: ${exchange.requestMethod}" }

		when (exchange.requestMethod) {
			Methods.GET -> get(exchange)
			else -> response405(exchange)
		}
	}

	private fun get(exchange: HttpServerExchange) {
		val info = Informer.info()
		logger.info { "generated info: $info" }
		exchange.responseHeaders.put(Headers.CONTENT_TYPE, APPLICATION_JSON)
		exchange.responseSender.send(mapper.writeValueAsString(info))
	}
}
