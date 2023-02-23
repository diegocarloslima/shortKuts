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

/**
 * Convenience property for a nullable [Collection] object. It will return the size of the object or
 * zero if the receiver is `null`.
 *
 * @see Collection.size
 */
inline val <T> Collection<T>?.size: Int get() = this?.size ?: 0

/**
 * Convenience function for a nullable [Collection] object. It will return `true` if the object is
 * empty or if the receiver is `null`.
 *
 * @see Collection.isEmpty
 */
inline fun <T> Collection<T>?.isEmpty(): Boolean = this?.isEmpty() ?: true

/**
 * Convenience function for a nullable [Collection] object. It will return `true` if the object is not
 * empty and false if it's empty or the receiver is `null
 *
 * @see Collection.isNotEmpty
 */
inline fun <T> Collection<T>?.isNotEmpty(): Boolean = !this.isEmpty()

/**
 * Convenience function for checking an element in a nullable [Collection] object.
 *
 * @param element [T] to be checked if it's contained in the collection.
 *
 * @return `false` if the receiver is `null` of it the element is not contained in this collection.
 * Returns `true` otherwise.
 *
 * @see Collection.contains
 */
inline operator fun <T> Collection<T>?.contains(element: @UnsafeVariance T): Boolean =
    this?.contains(element) ?: false

/**
 * Convenience function for checking elements in a nullable [Collection] object.
 *
 * @param elements [T] to be checked if they are contained in the collection.
 *
 * @return `false` if the receiver is `null` or if the element is not contained in this collection.
 * Returns `true` otherwise.
 *
 * @see Collection.containsAll
 */
inline fun <T> Collection<T>?.containsAll(elements: Collection<@UnsafeVariance T>): Boolean =
    this?.containsAll(elements) ?: false
