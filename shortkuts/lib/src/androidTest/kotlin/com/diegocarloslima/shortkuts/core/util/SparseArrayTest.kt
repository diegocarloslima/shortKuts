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

package com.diegocarloslima.shortkuts.core.util

import android.util.SparseArray
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SparseArrayTest {

    @Test
    fun sizeOfNull() {
        val nullValue: SparseArray<*>? = null
        assertEquals(0, nullValue.size)
    }

    @Test
    fun sizeOfNotNull() {
        val value = SparseArray<String>()
        value[0] = "foo"
        value[2] = "bar"

        val nullableValue: SparseArray<String>? = value
        assertEquals(value.size, nullableValue.size)
    }

    @Test
    fun isEmptyOfNull() {
        val nullValue: SparseArray<*>? = null
        assertTrue(nullValue.isEmpty())
        assertFalse(nullValue.isNotEmpty())
    }

    @Test
    fun isEmptyOfNotNull() {
        val value = SparseArray<String>()
        value[0] = "test"

        val nullableValue: SparseArray<String>? = value
        assertEquals(value.isEmpty(), nullableValue.isEmpty())
        assertEquals(value.isNotEmpty(), nullableValue.isNotEmpty())
    }

    @Test
    fun containsOfNull() {
        val nullValue: SparseArray<String>? = null
        assertFalse(nullValue.containsKey(0))
        assertFalse(nullValue.containsValue("test"))
    }

    @Test
    fun containsOfNotNull() {
        val value = SparseArray<String>()
        value[1] = "test"

        val nullableValue: SparseArray<String>? = value
        val value1 = "test"
        val value2 = "foo"

        val key0 = 0
        val key1 = 1

        assertEquals(value.containsKey(key0), nullableValue.containsKey(key0))
        assertEquals(value.containsKey(key1), nullableValue.containsKey(key1))
        assertFalse(nullableValue.containsKey(key0))
        assertTrue(nullableValue.containsKey(key1))

        assertEquals(value.containsValue(value1), nullableValue.containsValue(value1))
        assertEquals(value.containsValue(value2), nullableValue.containsValue(value2))
        assertTrue(nullableValue.containsValue(value1))
        assertFalse(nullableValue.containsValue(value2))
    }
}
