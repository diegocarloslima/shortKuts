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

package com.diegocarloslima.shortkuts.kotlin

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ArrayTest {

    @Test
    fun sizeOfNull() {
        val nullValue: Array<*>? = null
        assertEquals(0, nullValue.size)
    }

    @Test
    fun sizeOfNotNull() {
        val value = arrayOf("test")
        val nullableValue: Array<String>? = value
        assertEquals(value.size, nullableValue.size)
    }

    @Test
    fun isEmptyOfNull() {
        val nullValue: Array<*>? = null
        assertTrue(nullValue.isEmpty())
        assertFalse(nullValue.isNotEmpty())
    }

    @Test
    fun isEmptyOfNotNull() {
        val value = arrayOf("test")
        val nullableValue: Array<String>? = value
        assertEquals(value.isEmpty(), nullableValue.isEmpty())
        assertEquals(value.isNotEmpty(), nullableValue.isNotEmpty())
    }

    @Test
    fun containsOfNull() {
        val nullValue: Array<*>? = null
        val element = "foo"
        assertFalse(nullValue.contains(element))
    }

    @Test
    fun containsOfNotNull() {
        val value = arrayOf("test")
        val nullableValue: Array<String>? = value
        val element1 = "test"
        val element2 = "foo"

        assertEquals(value.contains(element1), nullableValue.contains(element1))
        assertEquals(value.contains(element2), nullableValue.contains(element2))
        assertTrue(nullableValue.contains(element1))
        assertFalse(nullableValue.contains(element2))
    }
}
