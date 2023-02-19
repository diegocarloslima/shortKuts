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

import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.core.os.ParcelCompat
import java.io.Serializable

/**
 * Uses [ParcelCompat] to read and return a new [Array] of [T] from the parcel at the current
 * dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new array or `null` if the previously written array was `null`.
 *
 * @see ParcelCompat.readArray
 */
inline fun <reified T> Parcel.readArraySk(loader: ClassLoader? = null): Array<T>? =
    ParcelCompat.readArray(this, loader, T::class.java)?.map { it as T }?.toTypedArray()

/**
 * Uses [ParcelCompat] to read and return a new [ArrayList] of [T] from the parcel at the current
 * dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new [ArrayList] or `null` if the previously written list was `null`.
 *
 * @see ParcelCompat.readArrayList
 */
inline fun <reified T> Parcel.readArrayListSk(loader: ClassLoader? = null): ArrayList<T>? =
    ParcelCompat.readArrayList(this, loader, T::class.java)

/**
 * Uses [ParcelCompat] to read a boolean value from the parcel at the current dataPosition().
 *
 * @return a boolean value.
 *
 * @see ParcelCompat.readArrayList
 */
inline fun Parcel.readBooleanSk(): Boolean = ParcelCompat.readBoolean(this)

/**
 * Uses [ParcelCompat] to read and return a new [HashMap] of [K] and [V] from the parcel at the
 * current dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new [HashMap] or `null` if the previously written map was `null`.
 *
 * @see ParcelCompat.readHashMap
 */
inline fun <reified K, reified V> Parcel.readHashMapSk(loader: ClassLoader? = null): HashMap<K, V>? =
    ParcelCompat.readHashMap(this, loader, K::class.java, V::class.java)

/**
 * Uses [ParcelCompat] to read into an existing [MutableList] of [T] from the parcel at the current
 * dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @see ParcelCompat.readList
 */
inline fun <reified T> Parcel.readListSk(
    outVal: MutableList<in T>,
    loader: ClassLoader? = null,
): Unit = ParcelCompat.readList(this, outVal, loader, T::class.java)

/**
 * Uses [ParcelCompat] to read into an existing [MutableMap] of [K] and [V] from the parcel at the
 * current dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @see ParcelCompat.readMap
 */
inline fun <reified K, reified V> Parcel.readMapSk(
    outVal: MutableMap<in K, in V>,
    loader: ClassLoader? = null,
): Unit = ParcelCompat.readMap(this, outVal, loader, K::class.java, V::class.java)

/**
 * Uses [ParcelCompat] to read and return a new [Parcelable] [P] from the parcel at the current
 * dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new [Parcelable] or `null` if the previously written parcelable was `null`.
 *
 * @see ParcelCompat.readParcelable
 */
inline fun <reified P : Parcelable> Parcel.readParcelableSk(loader: ClassLoader? = null): P? =
    ParcelCompat.readParcelable(this, loader, P::class.java)

// TODO: readParcelableArrayTyped

/**
 * Uses [ParcelCompat] to read and return a new [Parcelable.Creator] [T] from the parcel at the
 * current dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new [Parcelable.Creator] or `null` if the previously written creator was `null`.
 *
 * @see ParcelCompat.readParcelableCreator
 */
@RequiresApi(30)
inline fun <reified T> Parcel.readParcelableCreatorSk(
    loader: ClassLoader? = null,
): Parcelable.Creator<T>? = ParcelCompat.readParcelableCreator(this, loader, T::class.java)

/**
 * Uses [ParcelCompat] to read into an existing [MutableList] of [T] from the parcel at the current
 * dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @see ParcelCompat.readParcelableList
 */
@RequiresApi(29)
inline fun <reified T> Parcel.readParcelableListSk(
    list: MutableList<T>,
    loader: ClassLoader? = null,
) {
    ParcelCompat.readParcelableList(this, list, loader, T::class.java)
}

/**
 * Uses [ParcelCompat] to read and return a new [Serializable] [S] from the parcel at the
 * current dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new [Serializable] or `null` if the previously written serializable was `null`.
 *
 * @see ParcelCompat.readSerializable
 */
inline fun <reified S : Serializable> Parcel.readSerializableSk(loader: ClassLoader? = null): S? =
    ParcelCompat.readSerializable(this, loader, S::class.java)

/**
 * Uses [ParcelCompat] to read and return a new [SparseArray] of [T] from the parcel at the current
 * dataPosition().
 *
 * @param loader class loader that will be used to load any enclosed Parcelables.
 *
 * @return a new array or `null` if the previously written array was `null`.
 *
 * @see ParcelCompat.readSparseArray
 */
inline fun <reified T> Parcel.readSparseArraySk(loader: ClassLoader? = null): SparseArray<T>? =
    ParcelCompat.readSparseArray(this, loader, T::class.java)

/**
 * Uses [ParcelCompat] to write a boolean value into the parcel at the current dataPosition().
 *
 * @param value boolean value.
 *
 * @see ParcelCompat.writeBoolean
 */
inline fun Parcel.writeBooleanSk(value: Boolean): Unit = ParcelCompat.writeBoolean(this, value)
