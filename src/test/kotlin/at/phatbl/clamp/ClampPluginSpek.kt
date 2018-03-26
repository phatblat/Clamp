package at.phatbl.clamp

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertNotNull

object ClampPluginSpek : Spek({
    describe("Clamp Plugin") {
        lateinit var project: Project
        beforeEachTest {
            project = ProjectBuilder.builder().build()
            project.plugins.apply(ClampPlugin::class.java)
        }
        it("creates the wrapper task") {
            val task = project.tasks.getByName("wrapper")
            assertNotNull(task)
        }
    }
})
