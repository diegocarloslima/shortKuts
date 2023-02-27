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
import android.os.HandlerThread
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HandlerTest {

    private val thread = HandlerThread("handler-test")

    @Before
    fun before() {
        thread.start()
    }

    @After
    fun after() {
        thread.quit()
    }

    @Test
    fun postDelayed() {
        val handler = Handler(thread.looper)

        val latch = CountDownLatch(1)
        handler.postDelayedSk(300) {
            latch.countDown()
        }

        val events = mutableListOf<String>()
        val token = "token"

        handler.postDelayedSk(200, token) {
            events.add("event1")
        }

        handler.postDelayedSk(100, token) {
            events.add("event2")
            handler.removeCallbacksAndMessages(token)
        }

        handler.post { events.add("event0") }

        assertTrue(latch.await(1, TimeUnit.SECONDS))
        assertEquals(listOf("event0", "event2"), events)
    }
}
