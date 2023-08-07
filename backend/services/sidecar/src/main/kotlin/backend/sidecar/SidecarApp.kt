package backend.sidecar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.sidecar.EnableSidecar

@SpringBootApplication
@EnableSidecar
class SidecarApp

fun main(args: Array<String>) {
    runApplication<SidecarApp>(*args)
}
