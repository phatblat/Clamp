package at.phatbl.clamp

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/** Changes the os.name system property. */
fun changeOS(name: String) = System.setProperty("os.name", name) ?: ""

/** Restores the os.name system property. */
fun restoreOS() = System.setProperty("os.name", "Mac OS X") ?: ""

object ClampPluginSpek : Spek({
    describe("Clamp Plugin") {
        lateinit var project: Project
        lateinit var task: Task
        lateinit var shellScript: File
        lateinit var batchFile: File
        /** Setup in lambda so it can be called after tests set os.name */
        val setup = { osName: String? ->
            project = ProjectBuilder.builder().build()
            if (osName != null) { changeOS(osName) }
            project.plugins.apply(ClampPlugin::class.java)
            restoreOS()
            task = project.tasks.getByName("wrapper")
            shellScript = project.file("gradlew")
            shellScript.createNewFile()
            batchFile = project.file("gradlew.bat")
            batchFile.createNewFile()
            //projectDir/gradle/wrapper/gradle-wrapper.properties (No such file or directory)
            //at org.gradle.api.tasks.wrapper.Wrapper.writeProperties(Wrapper.java:189)
            //at org.gradle.api.tasks.wrapper.Wrapper.generate(Wrapper.java:126)
            project.file("gradle/wrapper").mkdirs()
        }
        it("applies the base plugin") {
            setup(null)
            val basePlugin = project.plugins.findPlugin("base")
            assertNotNull(basePlugin)
        }
        it("creates the wrapper task") {
            setup(null)
            assertNotNull(task)
        }
        on("windows") {
            setup("Windows 10")
            it("deletes the shell script file") {
                assertEquals("deleteShellScript", task.finalizedBy.getDependencies(task).first().name)
            }
        }
        on("mac") {
            setup("Mac OS X")
            it("deletes the batch file") {
                assertEquals("deleteBatchFile", task.finalizedBy.getDependencies(task).first().name)
            }
        }
        on("linux") {
            setup("Linux")
            it("deletes the batch file") {
                assertEquals("deleteBatchFile", task.finalizedBy.getDependencies(task).first().name)
            }
        }
    }
})
