package at.phatbl.clamp.tasks.wrapper

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.wrapper.Wrapper

/**
 * Configures the wrapper task.
 */
open class ConfigureWrapperTask : DefaultTask() {
    val defaultGradleVersion = "4.8.1"
    val defaultDistributionType = Wrapper.DistributionType.ALL

    @Input
    lateinit var wrapperTask: WrapperTask

    init {
        description = "Configures the Gradle wrapper installation."
        group = "🗜 Clamp"
    }

    @TaskAction
    fun configure() {
        wrapperTask.apply {
            gradleVersion = ""
            distributionType
        }
    }
}
