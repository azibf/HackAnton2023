package backend.broadcast

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = ["backend.broadcast", "backend.shared"])
class EventApp

fun main(args: Array<String>) {
    runApplication<EventApp>(*args)
}
