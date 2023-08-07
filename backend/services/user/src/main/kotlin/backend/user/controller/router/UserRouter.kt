package backend.user.controller.router

import backend.shared.controller.AbstractRouter
import backend.user.controller.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserRouter(
    private val handler: UserHandler,
) : AbstractRouter() {
    @Bean
    fun userApi() = api {
        "/user".nest {
            POST("/ids", handler::listById)
            GET("/token/{token}", handler::findByToken)
            "/telegram".nest {
                GET("/{id}", handler::findByChatId)
                POST(handler::linkTelegram)
                DELETE(handler::unlinkTelegram)
            }
            "/{id}".nest {
                GET(handler::findById)
                PUT(handler::update)
                DELETE(handler::delete)
            }
            GET(handler::list)
            POST(handler::create)
        }
    }
}
