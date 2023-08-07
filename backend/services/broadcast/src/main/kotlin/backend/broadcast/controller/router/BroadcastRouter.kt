package backend.broadcast.controller.router

import backend.broadcast.controller.handler.BroadcastHandler
import backend.shared.controller.AbstractRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BroadcastRouter(
    private val handler: BroadcastHandler,
) : AbstractRouter() {
    @Bean
    fun broadcastApi() = api {
        "/broadcast".nest {
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
