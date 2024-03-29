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
    implementation(project(":shared"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // DB
    runtimeOnly("mysql:mysql-connector-java:8.0.32")
}

jib {
    to {
        image = "ghcr.io/azibf/hackanton2023/challenge:latest"
    }
}
