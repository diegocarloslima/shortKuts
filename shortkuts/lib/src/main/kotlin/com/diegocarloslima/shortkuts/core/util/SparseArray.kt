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
import androidx.core.util.size
import androidx.core.util.containsKey as kContainsKey
import androidx.core.util.containsValue as kContainsValue
import androidx.core.util.isEmpty as kIsEmpty

/**
 * Convenience property for a nullable [SparseArray] object. It will return the size of the object or
 * zero if the receiver is `null`.
 *
 * @see SparseArray.size
 */
inline val <T> SparseArray<T>?.size: Int get() = this?.size() ?: 0

/**
 * Convenience function for a nullable [SparseArray] object. It will return `true` if the object is
 * empty or if the receiver is `null`.
 *
 * @see SparseArray.isEmpty
 */
inline fun <T> SparseArray<T>?.isEmpty(): Boolean = this?.kIsEmpty() ?: true

/**
 * Convenience function for a nullable [SparseArray] object. It will return `true` if the object is not
 * empty and false if it's empty or the receiver is `null
 *
 * @see SparseArray.isNotEmpty
 */
inline fun <T> SparseArray<T>?.isNotEmpty(): Boolean = !this.isEmpty()

/**
 * Convenience function for checking a key in a nullable [SparseArray] object.
 *
 * @param key to be checked if it's contained in the sparse array.
 *
 * @return `false` if the receiver is `null` of it the element is not contained in this sparse array.
 * Returns `true` otherwise.
 *
 * @see SparseArray.containsKey
 */
inline fun <T> SparseArray<T>?.containsKey(key: Int): Boolean = this?.kContainsKey(key) ?: false

/**
 * Convenience function for checking a value in a nullable [SparseArray] object.
 *
 * @param value [T] to be checked if it's contained in the sparse array.
 *
 * @return `false` if the receiver is `null` or if the element is not contained in this sparse array.
 * Returns `true` otherwise.
 *
 * @see SparseArray.containsValue
 */
inline fun <T> SparseArray<T>?.containsValue(value: T): Boolean =
    this?.kContainsValue(value) ?: false
