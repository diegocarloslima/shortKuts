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

import kotlin.collections.contains as kContains
import kotlin.collections.isEmpty as kIsEmpty

/**
 * Convenience property for a nullable [Array] object. It will return the size of the object or
 * zero if the receiver is `null`.
 *
 * @see Array.size
 */
inline val <T> Array<T>?.size: Int get() = this?.size ?: 0

/**
 * Convenience function for a nullable [Array] object. It will return `true` if the object is
 * empty or if the receiver is `null`.
 *
 * @see Array.isEmpty
 */
inline fun <T> Array<T>?.isEmpty(): Boolean = this?.kIsEmpty() ?: true

/**
 * Convenience function for a nullable [Array] object. It will return `true` if the object is not
 * empty and false if it's empty or the receiver is `null
 *
 * @see Array.isNotEmpty
 */
inline fun <T> Array<T>?.isNotEmpty(): Boolean = !this.isEmpty()

/**
 * Convenience function for checking an element in a nullable [Array] object.
 *
 * @param element [T] to be checked if it's contained in the array.
 *
 * @return `false` if the receiver is `null` of it the element is not contained in this array.
 * Returns `true` otherwise.
 *
 * @see Array.contains
 */
inline operator fun <T> Array<out T>?.contains(element: T): Boolean =
    this?.kContains(element) ?: false
