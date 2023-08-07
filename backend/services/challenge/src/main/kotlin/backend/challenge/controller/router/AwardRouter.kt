package backend.challenge.controller.router

import backend.challenge.controller.handler.AwardHandler
import backend.challenge.model.exception.FeignApiException
import backend.shared.controller.AbstractRouter
import feign.FeignException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwardRouter(
    private val handler: AwardHandler,
) : AbstractRouter() {
    override fun customExceptionHandler(throwable: Throwable) = when (throwable) {
        is FeignException.FeignClientException -> FeignApiException(throwable)
        else -> null
    }

    @Bean
    fun awardApi() = api {
        "/award".nest {
            "/{id}".nest {
                POST("/submit", handler::submit)
                GET(handler::findById)
                PUT(handler::update)
                DELETE(handler::delete)
            }
            GET(handler::list)
            POST(handler::create)
        }
    }
}
