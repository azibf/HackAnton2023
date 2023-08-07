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
    "sidecar",

    "user",
    "challenge",
    "event",
    "broadcast",

    "shared",
)
