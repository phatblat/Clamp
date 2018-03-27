package at.phatbl.clamp.tasks.wrapper

import at.phatbl.clamp.tasks.wrapper.util.executeActions
import org.gradle.api.Project
import org.gradle.api.tasks.wrapper.Wrapper
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

object DeleteBatchFileTaskSpek : Spek({
    describe("Delete Batch File Task") {
        lateinit var project: Project
        lateinit var task: DeleteBatchFileTask
        beforeEachTest {
            project = ProjectBuilder.builder().build()
            task = project.tasks.create("deleteBatchFile", DeleteBatchFileTask::class.java)
        }
        it("can be created") {
            assertNotNull(task)
        }
        it("deletes the batch file") {
            val file = project.file("gradlew.bat")
            file.createNewFile()
            assertTrue { file.exists() }
            task.executeActions()
            assertFalse { file.exists() }
        }
    }
})
