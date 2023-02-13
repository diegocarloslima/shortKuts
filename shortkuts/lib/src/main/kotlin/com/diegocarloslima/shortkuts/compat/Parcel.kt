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

inline fun <reified T> Parcel.readArray(loader: ClassLoader? = null): Array<T>? =
    ParcelCompat.readArray(this, loader, T::class.java)

inline fun <reified T> Parcel.readArrayList(loader: ClassLoader? = null): ArrayList<T>? =
    ParcelCompat.readArrayList(this, loader, T::class.java)

inline fun Parcel.readBoolean(): Boolean = ParcelCompat.readBoolean(this)

inline fun <reified K, reified V> Parcel.readHashMap(loader: ClassLoader? = null): HashMap<K, V>? =
    ParcelCompat.readHashMap(this, loader, K::class.java, V::class.java)

inline fun <reified T> Parcel.readList(
    outVal: MutableList<in T>,
    loader: ClassLoader? = null,
): Unit = ParcelCompat.readList(this, outVal, loader, T::class.java)

inline fun <reified K, reified V> Parcel.readMap(
    outVal: MutableMap<in K, in V>,
    loader: ClassLoader? = null,
): Unit = ParcelCompat.readMap(this, outVal, loader, K::class.java, V::class.java)

inline fun <reified P : Parcelable> Parcel.readParcelable(loader: ClassLoader? = null): P? =
    ParcelCompat.readParcelable(this, loader, P::class.java)

// TODO: readParcelableArrayTyped

@RequiresApi(30)
inline fun <reified T> Parcel.readParcelableCreator(
    loader: ClassLoader? = null,
): Parcelable.Creator<T>? = ParcelCompat.readParcelableCreator(this, loader, T::class.java)

@RequiresApi(29)
inline fun <reified T> Parcel.readParcelableList(
    list: MutableList<T>,
    loader: ClassLoader? = null,
): MutableList<T> = ParcelCompat.readParcelableList(this, list, loader, T::class.java)

inline fun <reified S : Serializable> Parcel.readSerializable(loader: ClassLoader? = null): S? =
    ParcelCompat.readSerializable(this, loader, S::class.java)

inline fun <reified T> Parcel.readSparseArray(loader: ClassLoader? = null): SparseArray<T>? =
    ParcelCompat.readSparseArray(this, loader, T::class.java)

inline fun Parcel.writeBoolean(value: Boolean): Unit = ParcelCompat.writeBoolean(this, value)
