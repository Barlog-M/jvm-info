package app.handler

import app.bad
import app.ok
import io.undertow.util.StatusCodes
import io.undertow.server.HttpServerExchange
import io.undertow.util.Headers

val APPLICATION_JSON = "application/json"

fun responseEmptyBodyWith(status: Int, exchange: HttpServerExchange) {
	exchange.statusCode = status
	exchange.responseSender.send("")
}

fun responseEmptyObjectWith(status: Int, exchange: HttpServerExchange) {
	exchange.responseHeaders.put(Headers.CONTENT_TYPE, APPLICATION_JSON)
	exchange.statusCode = status
	exchange.responseSender.send("{}")
}

fun response200(exchange: HttpServerExchange) {
	responseEmptyBodyWith(StatusCodes.OK, exchange)
}

fun response200EmptyObject(exchange: HttpServerExchange) {
	responseEmptyObjectWith(StatusCodes.OK, exchange)
}

fun response404(exchange: HttpServerExchange) {
	responseEmptyBodyWith(StatusCodes.NOT_FOUND, exchange)
}

fun response405(exchange: HttpServerExchange) {
	responseEmptyBodyWith(StatusCodes.METHOD_NOT_ALLOWED, exchange)
}

fun response400(exchange: HttpServerExchange) {
	responseEmptyBodyWith(StatusCodes.BAD_REQUEST, exchange)
}

fun response500(exchange: HttpServerExchange) {
	responseEmptyBodyWith(StatusCodes.INTERNAL_SERVER_ERROR, exchange)
}

fun getQueryParam(name: String, exchange: HttpServerExchange): Pair<Boolean, String> {
	val queryParams = exchange.queryParameters
	val param = queryParams[name]
	if (param == null) {
		response404(exchange)
		return bad("")
	}

	val value = param.first
	if (value.isNullOrBlank()) {
		response404(exchange)
		return bad("")
	}

	return ok(value)
}

fun getQueryIntParam(name: String, exchange: HttpServerExchange): Pair<Boolean, Int> {
	val (err, param) = getQueryParam(name, exchange)
	if (!err) {
		return try {
			val r = param.toInt()
			ok(r)
		} catch (e: NumberFormatException) {
			response404(exchange)
			bad(-1)
		}
	}
	return bad(-1)
}
