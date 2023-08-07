package backend.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = ["backend.user", "backend.shared"])
class UserApp

fun main(args: Array<String>) {
    runApplication<UserApp>(*args)
}
