package at.phatbl.clamp

import at.phatbl.clamp.tasks.wrapper.DeleteBatchFileTask
import at.phatbl.clamp.tasks.wrapper.DeleteShellScriptFileTask
import at.phatbl.clamp.tasks.wrapper.WrapperTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

/**
 * Get DRY by clamping down on boilerplate in your Gradle scripts.
 */
class ClampPlugin : Plugin<Project> {
    /** Reference to the project being configured. */
    lateinit var project: Project

    /**
     * Applies the Clamp plugin to the project.
     */
    override fun apply(nullableProject: Project?) {
        project = nullableProject ?: return

        project.plugins.apply(BasePlugin::class.java)

        val wrapper = project.tasks.create("wrapper", WrapperTask::class.java)
        val deleteFileTask = when (System.getProperty("os.name").toLowerCase().contains("win")) {
            true  -> project.tasks.create("deleteShellScript", DeleteShellScriptFileTask::class.java)
            false -> project.tasks.create("deleteBatchFile", DeleteBatchFileTask::class.java)
        }
        wrapper.finalizedBy(deleteFileTask)
    }
}
