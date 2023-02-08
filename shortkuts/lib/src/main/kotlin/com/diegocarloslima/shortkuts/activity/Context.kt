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

package com.diegocarloslima.shortkuts.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent

/**
 * Convenience function to start an [Activity] [A].
 * It can optionally execute [intentBlock] on an [Intent] receiver for additional configuration.
 *
 * @throws ActivityNotFoundException if there was no [Activity] found.
 *
 * @see Context.startActivity
 */
@Throws(ActivityNotFoundException::class)
inline fun <reified A : Activity> Context.startActivity(intentBlock: Intent.() -> Unit = {}) {
    startActivity(Intent(this, A::class.java).apply(intentBlock))
}

/**
 * Convenience function to start an [Activity] based on an [Intent] [action].
 * It can optionally execute [intentBlock] on an [Intent] receiver for additional configuration.
 *
 * @param action An action name, such as [Intent.ACTION_VIEW]
 *
 * @throws ActivityNotFoundException if there was no [Activity] found.
 *
 * @see Context.startActivity
 * @see Intent.setAction
 */
@Throws(ActivityNotFoundException::class)
fun Context.startActivity(action: String, intentBlock: Intent.() -> Unit = {}) {
    startActivity(Intent(action).apply(intentBlock))
}
