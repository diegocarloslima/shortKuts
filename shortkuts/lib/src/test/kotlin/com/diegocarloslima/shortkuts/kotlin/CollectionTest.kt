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

class CollectionTest {

    @Test
    fun sizeOfNull() {
        val nullValue: Collection<*>? = null
        assertEquals(0, nullValue.size)
    }

    @Test
    fun sizeOfNotNull() {
        val value = listOf("test")
        val nullableValue: List<String>? = value
        assertEquals(value.size, nullableValue.size)
    }

    @Test
    fun isEmptyOfNull() {
        val nullValue: Collection<*>? = null
        assertTrue(nullValue.isEmpty())
    }

    @Test
    fun isEmptyOfNotNull() {
        val value = listOf("test")
        val nullableValue: List<String>? = value
        assertEquals(value.isEmpty(), nullableValue.isEmpty())
    }

    @Test
    fun containsOfNull() {
        val nullValue: Collection<*>? = null
        val element = "foo"
        assertFalse(nullValue.contains(element))
    }

    @Test
    fun containsOfNotNull() {
        val value = listOf("test")
        val nullableValue: List<String>? = value
        val element1 = "test"
        val element2 = "foo"

        assertEquals(value.contains(element1), nullableValue.contains(element1))
        assertEquals(value.contains(element2), nullableValue.contains(element2))
        assertTrue(nullableValue.contains(element1))
        assertFalse(nullableValue.contains(element2))
    }

    @Test
    fun containsAllOfNull() {
        val nullValue: Collection<*>? = null
        val elements = listOf(1, 2, 3)
        assertFalse(nullValue.containsAll(elements))
    }

    @Test
    fun containsAllOfNotNull() {
        val value = listOf(1, 2, 3, 4)
        val nullableValue: List<Int>? = value
        val elements1 = listOf(1, 2, 3)
        val elements2 = listOf(4, 5)

        assertEquals(value.containsAll(elements1), nullableValue.containsAll(elements1))
        assertEquals(value.containsAll(elements2), nullableValue.containsAll(elements2))
        assertTrue(nullableValue.containsAll(elements1))
        assertFalse(nullableValue.containsAll(elements2))
    }
}
