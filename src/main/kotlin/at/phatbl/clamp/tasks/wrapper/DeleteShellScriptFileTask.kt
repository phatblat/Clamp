package at.phatbl.clamp.tasks.wrapper

import org.gradle.api.tasks.Delete

/**
 * Deletes the Gradle wrapper shell script.
 */
open class DeleteShellScriptFileTask : Delete() {
    init {
        delete = setOf("gradlew")
    }
}
