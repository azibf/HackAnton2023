package backend.user.controller.router

import backend.shared.controller.AbstractRouter
import backend.user.controller.handler.TeamHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TeamRouter(
    private val handler: TeamHandler,
) : AbstractRouter() {
    @Bean
    fun teamApi() = api {
        "/team".nest {
            POST("/ids", handler::listById)
            "/{id}".nest {
                "/participant".nest {
                    GET(handler::listParticipants)
                    POST(handler::participate)
                }
                GET(handler::findById)
                PUT(handler::update)
                DELETE(handler::delete)
            }
            GET(handler::list)
            POST(handler::create)
        }
    }
}
