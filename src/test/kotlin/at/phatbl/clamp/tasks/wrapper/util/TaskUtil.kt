package at.phatbl.clamp.tasks.wrapper.util

import org.gradle.api.Task

/** Execute all task actions. */
fun Task.executeActions() = actions.forEach { it.execute(this) }
