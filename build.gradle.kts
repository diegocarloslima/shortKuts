/*
 * Copyright 2023 Diego Carlos Lima <https://diegocarloslima.com/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.diffplug.gradle.spotless.SpotlessExtension

@Suppress("DSL_SCOPE_VIOLATION")
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
                mapOf(
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
                mapOf(
                    "ktlint_code_style" to "android",
                ),
            )
            // Look for the first line that starts with a word or a '@' character (not a comment)
            licenseHeaderFile(rootProject.file("spotless/copyright.kts.txt"), """^[\w@]""")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
            targetExclude(".idea/**/*.xml")
            indentWithSpaces()
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("markdown") {
            target("**/*.md")
            indentWithSpaces()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}
