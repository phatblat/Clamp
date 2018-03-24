import org.gradle.internal.impldep.org.apache.ivy.core.IvyPatternHelper.substitute
import java.io.File

/*
 * settings.gradle.kts
 * Clamp
 */

rootProject.name = "Clamp"

/*
 * Clone TubeGradlePlugin to sibling directory to build from source.
 */
val otherProject = file("../TubeGradlePlugin/")
if (otherProject.exists()) {
    includeBuild(otherProject) {
        dependencySubstitution {
            substitute(module("at.phatbl:tube")).with(project(":"))
        }
    }
}

// Workaround to make the JUnit Platform Gradle Plugin available using the `plugins` DSL
// See: https://github.com/junit-team/junit5/issues/768#issuecomment-330078905
pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "at.phatbl.tube" ->
                    useModule("at.phatbl:tube:${requested.version}")
                "org.junit.platform.gradle.plugin" ->
                    useModule("org.junit.platform:junit-platform-gradle-plugin:${requested.version}")
                else -> println("")
            }
        }
    }
}
