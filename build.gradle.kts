import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.spotless) apply false
}

allprojects {
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            // ktlint should be before licenseHeaderFile
            ktlint(libs.versions.ktlint.get()).userData(
                kotlin.collections.mapOf(
                    "ktlint_code_style" to "android",
                ),
            )
            licenseHeaderFile(rootProject.file("spotless/copyright.kt.txt"))
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            // ktlint should be before licenseHeaderFile
            ktlint(libs.versions.ktlint.get()).userData(
                kotlin.collections.mapOf(
                    "ktlint_code_style" to "android",
                ),
            )
            // Look for the first line that starts with a word character (not a comment)
            licenseHeaderFile(rootProject.file("spotless/copyright.kts.txt"), "(^\\w)")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
            targetExclude(".idea/**/*.xml")
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("markdown") {
            target("**/*.md")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}
