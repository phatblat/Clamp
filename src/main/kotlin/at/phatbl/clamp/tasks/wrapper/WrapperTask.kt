package at.phatbl.clamp.tasks.wrapper

import org.gradle.api.tasks.wrapper.Wrapper

/**
 * Opinionated wrapper task.
 */
open class WrapperTask : Wrapper() {
    init {
        description = "Upstalls the Gradle wrapper."
        group = "🗜 Clamp"

        gradleVersion = "4.8.1"
        distributionType = DistributionType.ALL
    }
}
