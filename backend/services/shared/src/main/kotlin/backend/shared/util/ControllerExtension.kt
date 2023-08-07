package backend.shared.util

import backend.shared.model.exception.MalformedRequestException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.paramOrNull

fun ServerRequest.jsonPathVariable(name: String): String {
    var value = try {
        pathVariable(name)
    } catch (e: IllegalArgumentException) {
        throw Exception("Path variable [$name] is not provided")
    }
    if (!value.startsWith("\"")) value = "\"$value"
    if (!value.endsWith("\"")) value = "$value\""
    return value
}

fun ServerRequest.jsonParam(name: String): String? {
    var value = paramOrNull(name) ?: return null
    if (!value.startsWith("\"")) value = "\"$value"
    if (!value.endsWith("\"")) value = "$value\""
    return value
}

inline fun <reified T : Any> ServerRequest.pathVariableOrThrow(name: String): T {
    val value = jsonPathVariable(name)
    val mapper = jacksonObjectMapper().also { it.findAndRegisterModules() }
    return mapper.readValue(value, T::class.java)
}

inline fun <reified T> ServerRequest.paramOrThrow(name: String): T {
    if (param(name).isEmpty) throw MalformedRequestException()
    val value = jsonParam(name)
    val mapper = jacksonObjectMapper()
    return mapper.readValue(value, T::class.java)
}

inline fun <reified T> ServerRequest.paramOrElse(name: String, default: () -> T): T {
    val value = jsonParam(name) ?: return default()
    val mapper = jacksonObjectMapper().also { it.findAndRegisterModules() }
    return try {
        mapper.readValue(value, T::class.java)
    } catch (e: JsonProcessingException) {
        default()
    }
}
