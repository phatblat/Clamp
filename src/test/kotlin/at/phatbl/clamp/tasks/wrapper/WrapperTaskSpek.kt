package at.phatbl.clamp.tasks.wrapper

import org.gradle.api.Project
import org.gradle.api.tasks.wrapper.Wrapper
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object WrapperTaskSpek : Spek({
    describe("Wrapper Task") {
        lateinit var project: Project
        lateinit var task: WrapperTask
        beforeEachTest {
            project = ProjectBuilder.builder().build()
            task = project.tasks.create("wrapper", WrapperTask::class.java)
        }
        it("can be created") {
            assertNotNull(task)
        }
        it("uses the ALL distribution") {
            assertEquals(Wrapper.DistributionType.ALL, task.distributionType)
        }
    }
})
