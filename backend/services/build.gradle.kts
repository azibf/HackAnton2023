import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false

    id("org.springframework.boot") version "3.1.1" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    id("com.google.cloud.tools.jib") version "3.3.1" apply false
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val springCloudVersion = "2022.0.3"
extra["springCloudVersion"] = springCloudVersion

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.allopen")
    }

    configure<AllOpenExtension> {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    tasks {
        withType<Test> {
            useJUnitPlatform()
        }

        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all-compatibility")
                jvmTarget = "17"
            }
        }
    }

    dependencies {
        implementation(kotlin("reflect"))

        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        // Spring Cloud
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))
        implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
        implementation("org.springframework.cloud:spring-cloud-starter-config")

        if (project.name != "config") {
            implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
        }

        // Serialization
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // Utils
        implementation("org.apache.commons:commons-io:1.3.2")

        // Testing
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
