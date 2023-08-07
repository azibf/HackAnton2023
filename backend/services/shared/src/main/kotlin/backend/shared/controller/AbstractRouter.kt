package backend.shared.controller

import backend.shared.config.localization.Localization
import backend.shared.config.logging.Logger
import backend.shared.model.exception.AbstractApiException
import backend.shared.model.exception.InternalServerException
import backend.shared.model.exception.MalformedRequestException
import backend.shared.util.ApiConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.servlet.function.RouterFunctionDsl
import org.springframework.web.servlet.function.router
import java.io.IOException

abstract class AbstractRouter {
    @Autowired
    private lateinit var logger: Logger

    @Autowired
    private lateinit var localization: Localization

    open fun customExceptionHandler(throwable: Throwable): AbstractApiException? = null

    private fun exceptionHandler(throwable: Throwable) = when (throwable) {
        is AbstractApiException -> throwable

        is IOException,
        is HttpMessageConversionException,
        is HttpMediaTypeNotSupportedException,
        -> MalformedRequestException(throwable.localizedMessage)

        else -> customExceptionHandler(throwable)
    }

    fun api(version: String = ApiConstant.V1, routes: RouterFunctionDsl.() -> Unit) = router {
        "${ApiConstant.PREFIX}/$version".nest { routes() }

        onError<Throwable> { throwable, _ ->
            val exception = exceptionHandler(throwable) ?: InternalServerException()
            exception.also {
                if (it.code.is5xxServerError) logger.error(throwable)
            }.asResponse(localization)
        }
    }
}
