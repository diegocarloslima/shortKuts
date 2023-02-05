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

import android.content.ContextWrapper
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ContextCompatTest {
    private val context = ApplicationProvider.getApplicationContext() as android.content.Context

    @Test
    fun getSystemServiceName() {
        val contextWrapper = object : ContextWrapper(context) {
            override fun getSystemServiceName(serviceClass: Class<*>): String? {
                return if (serviceClass == Unit::class.java) "unit" else null
            }
        }
        val unitValue = contextWrapper.getSystemServiceName<Unit>()
        val stringValue = contextWrapper.getSystemServiceName<String>()
        assertEquals("unit", unitValue)
        assertNull(stringValue)
    }
}
