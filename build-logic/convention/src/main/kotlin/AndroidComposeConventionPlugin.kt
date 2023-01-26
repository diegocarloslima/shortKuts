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

import com.android.build.api.dsl.CommonExtension
import com.diegocarloslima.shortkuts.buildlogic.androidTestImplementation
import com.diegocarloslima.shortkuts.buildlogic.getLibrary
import com.diegocarloslima.shortkuts.buildlogic.getVersion
import com.diegocarloslima.shortkuts.buildlogic.implementation
import com.diegocarloslima.shortkuts.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<CommonExtension<*, *, *, *>>("android") {
                buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.getVersion("androidxComposeCompiler").toString()
                }

                dependencies {
                    val bom = libs.getLibrary("androidx-compose-bom")
                    implementation(platform(bom))
                    androidTestImplementation(platform(bom))
                }
            }
        }
    }
}
