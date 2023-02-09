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

import android.app.LocaleManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityManager
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SdkSuppress
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ContextCompatTest {
    private val context = ApplicationProvider.getApplicationContext() as Context

    @Test
    fun getSystemServiceName() {
        val accessibilityServiceName = context.getSystemServiceName<AccessibilityManager>()
        val invalidServiceName = context.getSystemServiceName<String>()

        assertEquals(Context.ACCESSIBILITY_SERVICE, accessibilityServiceName)
        assertNull(invalidServiceName)
    }

    @Test
    @SdkSuppress(minSdkVersion = 23)
    fun getSystemServiceNameApi23() {
        val called = AtomicBoolean()
        val contextWrapper = object : ContextWrapper(context) {
            override fun getSystemServiceName(serviceClass: Class<*>): String? {
                called.set(true)
                return super.getSystemServiceName(serviceClass)
            }
        }
        val layoutInflaterServiceName = contextWrapper.getSystemServiceName<LayoutInflater>()

        assertEquals(Context.LAYOUT_INFLATER_SERVICE, layoutInflaterServiceName)
        assertTrue(called.get())
    }

    @Test
    @SdkSuppress(minSdkVersion = 33)
    fun getSystemServiceNameApi33() {
        val localeServiceName = context.getSystemServiceName<LocaleManager>()
        assertEquals(Context.LOCALE_SERVICE, localeServiceName)
    }

    @Test
    @SdkSuppress(maxSdkVersion = 25)
    fun registerReceiver() {
        val called = AtomicBoolean()
        val contextWrapper = object : ContextWrapper(context) {
            override fun registerReceiver(
                receiver: BroadcastReceiver?,
                filter: IntentFilter?,
                broadcastPermission: String?,
                scheduler: Handler?,
            ): Intent? {
                called.set(true)
                return super.registerReceiver(receiver, filter, broadcastPermission, scheduler)
            }
        }

        contextWrapper.registerReceiver(
            IntentFilter(),
            ContextCompat.RECEIVER_EXPORTED,
            TestReceiver(),
        )

        assertTrue(called.get())
    }

    @Test
    @SdkSuppress(minSdkVersion = 26)
    fun registerReceiverApi26() {
        val called = AtomicBoolean()
        val contextWrapper = object : ContextWrapper(context) {
            override fun registerReceiver(
                receiver: BroadcastReceiver?,
                filter: IntentFilter?,
                broadcastPermission: String?,
                scheduler: Handler?,
            ): Intent? {
                called.set(true)
                return super.registerReceiver(receiver, filter, broadcastPermission, scheduler)
            }
        }

        contextWrapper.registerReceiver(
            IntentFilter(),
            ContextCompat.RECEIVER_NOT_EXPORTED,
            TestReceiver(),
        )

        assertTrue(called.get())
    }

    @Test(expected = IllegalArgumentException::class)
    fun registerReceiverNoFlags() {
        context.registerReceiver(IntentFilter(), 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun registerReceiverBothFlags() {
        context.registerReceiver(
            IntentFilter(),
            ContextCompat.RECEIVER_EXPORTED or ContextCompat.RECEIVER_NOT_EXPORTED,
        )
    }
}

private class TestReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
    }
}
