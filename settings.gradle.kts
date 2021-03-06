/*
 * settings.gradle.kts
 * Clamp
 */

rootProject.name = "Clamp"

// Workaround to make the JUnit Platform Gradle Plugin available using the `plugins` DSL
// See: https://github.com/junit-team/junit5/issues/768#issuecomment-330078905
pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.junit.platform.gradle.plugin" ->
                    useModule("org.junit.platform:junit-platform-gradle-plugin:${requested.version}")
                else -> Unit
            }
        }
    }
}

// https://docs.gradle.org/4.8.1/userguide/publishing_maven.html#publishing_maven:deferred_configuration
enableFeaturePreview("STABLE_PUBLISHING")
