package backend.challenge.controller.router

import backend.challenge.controller.handler.ScoreHandler
import backend.challenge.model.exception.FeignApiException
import backend.shared.controller.AbstractRouter
import feign.FeignException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ScoreRouter(
    private val handler: ScoreHandler,
) : AbstractRouter() {
    override fun customExceptionHandler(throwable: Throwable) = when (throwable) {
        is FeignException.FeignClientException -> FeignApiException(throwable)
        else -> null
    }

    @Bean
    fun scoreApi() = api {
        "/score".nest {
            GET("/profile/{id}", handler::profileScore)
            "/user".nest {
                "/{id}".nest {
                    GET("/history", handler::userHistory)
                    GET(handler::userScore)
                }
                GET(handler::userScoreboard)
            }
            "/team".nest {
                "/{id}".nest {
                    GET("/history", handler::teamHistory)
                    GET(handler::teamScore)
                }
                GET(handler::teamScoreboard)
            }
        }
    }
}
