package backend.shared.controller

import backend.shared.config.localization.Localization
import backend.shared.model.ApiResponse
import backend.shared.model.PageRequest
import backend.shared.model.exception.ValidationException
import backend.shared.model.message.ApiMessage
import backend.shared.util.RequestConstant
import backend.shared.util.paramOrElse
import jakarta.validation.Validator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.body

abstract class AbstractHandler {
    @Autowired
    lateinit var validator: Validator

    @Autowired
    private lateinit var localization: Localization

    fun ServerRequest.page(
        defaultPage: Int = RequestConstant.DEFAULT_PAGE_NUMBER,
        defaultSize: Int = RequestConstant.DEFAULT_PAGE_SIZE,
        defaultOrder: Sort.Direction = Sort.DEFAULT_DIRECTION,
        defaultField: String = RequestConstant.DEFAULT_PAGE_FIELD,
    ) = PageRequest(
        page = paramOrElse("page") { defaultPage },
        size = paramOrElse("size") { defaultSize },
        order = paramOrElse("order") { defaultOrder },
        field = paramOrElse("field") { defaultField },
    )

    inline fun <reified T : Any> ServerRequest.validated(): T {
        val body = body<T>()
        val violations = validator.validate(body)
        if (violations.isNotEmpty()) {
            throw ValidationException(violations)
        }
        return body
    }

    fun <T> T.asMessage(message: String) = object : ApiMessage<T> {
        override val data = if (this@asMessage != Unit) this@asMessage else null
        override val message = localization.i18n(message)
    }.asResponse()

    fun <T> T.asMessage() = object : ApiResponse {
        val data = if (this@asMessage != Unit) this@asMessage else null
    }.asResponse()
}
