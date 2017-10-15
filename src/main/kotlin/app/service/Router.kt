package app.service

import app.handler.InfoHandler
import io.undertow.Handlers
import io.undertow.server.handlers.PathTemplateHandler

object Router {
	val handler: PathTemplateHandler = Handlers.pathTemplate()

	init {
		handler.add("/info", InfoHandler)
	}
}
