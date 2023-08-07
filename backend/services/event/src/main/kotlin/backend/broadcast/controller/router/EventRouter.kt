package backend.broadcast.controller.router

import backend.broadcast.controller.handler.EventHandler
import backend.shared.controller.AbstractRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventRouter(
    private val handler: EventHandler,
) : AbstractRouter() {
    @Bean
    fun eventApi() = api {
        "/event".nest {
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
