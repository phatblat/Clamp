package at.phatbl.clamp

import at.phatbl.clamp.tasks.wrapper.WrapperTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class ClampPlugin: Plugin<Project> {
    lateinit var project: Project
    override fun apply(nullableProject: Project?) {
        project = nullableProject ?: return

        project.tasks.create("wrapper", WrapperTask::class.java)
    }
}
