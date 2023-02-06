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
import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityManager
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
}
