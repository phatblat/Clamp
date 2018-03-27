package at.phatbl.clamp.tasks.wrapper

import org.gradle.api.tasks.Delete

/**
 * Deletes the Gradle wrapper batch file.
 */
open class DeleteBatchFileTask : Delete() {
    init {
        delete = setOf("gradlew.bat")
    }
}
