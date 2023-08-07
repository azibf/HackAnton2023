package backend.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class GatewayApp

fun main(args: Array<String>) {
    runApplication<GatewayApp>(*args)
}
