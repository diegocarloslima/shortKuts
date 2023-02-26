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

package com.diegocarloslima.shortkuts.compat

import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat

/**
 * Uses [HandlerCompat] to check if there are any pending posts of messages with callback `runnable`
 * in the message queue.
 *
 * @param runnable the callback to look for in the message queue.
 *
 * @return `true` if the callback is in the message queue.
 *
 * @see HandlerCompat.hasCallbacks
 */
fun Handler.hasCallbacksSk(runnable: Runnable): Boolean =
    HandlerCompat.hasCallbacks(this, runnable)

/**
 * Uses [HandlerCompat] to add a [Runnable] to the message queue, to be run after the specified
 * amount of time elapses.
 *
 * @param delayMillis The delay (in milliseconds) until the Runnable will be executed.
 *
 * @param token An instance which can be used to cancel `runnable` via
 * [Handler.removeCallbacksAndMessages].
 *
 * @param runnable the runnable that will be executed.
 *
 * @return `true` if the runnable was successfully placed in to the message queue.
 *
 * @see HandlerCompat.postDelayed
 */
fun Handler.postDelayedSk(delayMillis: Long, token: Any? = null, runnable: Runnable): Boolean =
    HandlerCompat.postDelayed(this, runnable, token, delayMillis)

object HandlerSk {

    /**
     * Uses [HandlerCompat] to create a new [Handler] whose posted messages and runnables are not
     * subject to synchronization barriers such as display vsync.
     *
     * @param looper the Looper that the new Handler should be bound to.
     *
     * @param callback for handle message.
     *
     * @return new async [Handler] instance.
     *
     * @see HandlerCompat.createAsync
     */
    fun createAsync(looper: Looper, callback: Handler.Callback? = null): Handler =
        if (callback != null) {
            HandlerCompat.createAsync(looper, callback)
        } else {
            HandlerCompat.createAsync(looper)
        }
}
