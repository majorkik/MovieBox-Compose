import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Locale

@Suppress("unused")
class GradleVersionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        plugins.apply("com.github.ben-manes.versions")

        tasks.named("dependencyUpdates", DependencyUpdatesTask::class.java).configure {
            rejectVersionIf {
                isNonStable(candidate.version)
            }
        }
    }

    private fun isNonStable(version: String): Boolean {
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.uppercase(Locale.getDefault()).contains(it)
        }

        val isStable = stableKeyword or regex.matches(version)

        return isStable.not()
    }
}
