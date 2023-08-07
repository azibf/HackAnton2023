package backend.shared.interceptor

import backend.shared.config.logging.Logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponseWrapper
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.io.CharArrayWriter
import java.io.PrintWriter


@Component
class RequestInterceptor(
    private val logger: Logger
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info(
            """
                Request: ${request.method} ${request.requestURI}
                Query String: ${request.queryString}
                Headers: ${ServletServerHttpRequest(request).headers}
            """.trimIndent()
        )
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?
    ) {
        logger.info(
            """
                Response Status: ${response.status}
                Headers: ${ServletServerHttpResponse(response).headers}
                Response Body: ${getResponseBody(response)}
            """.trimIndent()
        )
    }

    private fun getResponseBody(response: HttpServletResponse): String {
        val wrapper = ResponseWrapper(response)
        return wrapper.getResponseBody().ifEmpty { "No Response Body" }
    }

    private class ResponseWrapper(response: HttpServletResponse) : HttpServletResponseWrapper(response) {
        private val charArrayWriter = CharArrayWriter()

        override fun getWriter(): PrintWriter = PrintWriter(outputStream)

        fun getResponseBody(): String {
            flushBuffer()
            return charArrayWriter.toString()
        }
    }
}
