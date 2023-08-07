rootProject.name = "services"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(
    "config",
    "discovery",
    "gateway",

    "user",
    "challenge",
    "event",
    "broadcast",

    "shared",
)
