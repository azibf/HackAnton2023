package backend.challenge.controller.router

import backend.challenge.controller.handler.ChallengeHandler
import backend.challenge.model.exception.FeignApiException
import backend.shared.controller.AbstractRouter
import feign.FeignException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChallengeRouter(
    private val handler: ChallengeHandler,
) : AbstractRouter() {
    override fun customExceptionHandler(throwable: Throwable) = when (throwable) {
        is FeignException.FeignClientException -> FeignApiException(throwable)
        else -> null
    }

    @Bean
    fun challengeApi() = api {
        "/challenge".nest {
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
