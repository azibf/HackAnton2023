plugins {
    kotlin("jvm")
    kotlin("plugin.spring")

    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib")
}

group = "backend"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-netflix-sidecar:2.2.10.RELEASE")
}

jib {
    to {
        image = "ghcr.io/azibf/hackanton2023/sidecar:latest"
    }
}
