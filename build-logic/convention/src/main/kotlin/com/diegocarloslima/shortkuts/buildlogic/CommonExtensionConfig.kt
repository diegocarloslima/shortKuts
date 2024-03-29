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

package com.diegocarloslima.shortkuts.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureCommonAndroid(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = BuildConfig.Android.COMPILE_SDK

        defaultConfig {
            minSdk = BuildConfig.Android.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = BuildConfig.Java.VERSION
            targetCompatibility = BuildConfig.Java.VERSION
        }

        kotlinOptions {
            jvmTarget = BuildConfig.Java.VERSION.toString()
        }
    }
}
