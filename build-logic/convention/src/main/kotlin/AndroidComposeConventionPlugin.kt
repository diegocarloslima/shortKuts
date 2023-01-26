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
