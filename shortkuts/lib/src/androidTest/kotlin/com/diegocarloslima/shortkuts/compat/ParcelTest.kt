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

import android.content.pm.Signature
import android.graphics.Rect
import android.os.Parcel
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.getOrDefault
import androidx.test.filters.SdkSuppress
import com.diegocarloslima.shortkuts.core.util.size
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue

class ParcelTest {

    @Test
    fun readWriteBoolean() {
        withParcel {
            this.writeBooleanSk(true)
            this.writeBooleanSk(false)

            this.setDataPosition(0)
            assertTrue(this.readBooleanSk())
            assertFalse(this.readBooleanSk())
        }
    }

    @Test
    fun readArray() {
        withParcel {
            val array = arrayOf(Signature("1234"), null, Signature("abcd"))
            this.writeArray(array)

            this.setDataPosition(0)
            val readArray = this.readArraySk<Signature?>(Signature::class.java.classLoader)
            assertArrayEquals(array, readArray)
        }
    }

    @Test
    fun readArrayList() {
        withParcel {
            val arraylist = arrayListOf(Signature("1234"), null, Signature("abcd"))
            this.writeList(arraylist)

            this.setDataPosition(0)
            val readArrayList = this.readArrayListSk<Signature?>(Signature::class.java.classLoader)
            assertEquals(arraylist, readArrayList)
        }
    }

    @Test
    fun readHashMap() {
        withParcel {
            val hashMap = hashMapOf(
                "foo" to Signature("1234"),
                "bar" to Signature("abcd"),
                "lorem" to null,
            )
            this.writeMap(hashMap)

            this.setDataPosition(0)
            val readHashMap =
                this.readHashMapSk<String, Signature?>(Signature::class.java.classLoader)
            assertEquals(hashMap, readHashMap)
        }
    }

    @Test
    fun readList() {
        withParcel {
            val list = listOf(Signature("1234"), null, Signature("abcd"))
            this.writeList(list)

            this.setDataPosition(0)
            val readList = mutableListOf<Signature?>()
            this.readListSk(readList, Signature::class.java.classLoader)
            assertEquals(list, readList)
        }
    }

    @Test
    fun readMap() {
        withParcel {
            val hashMap = hashMapOf(
                "foo" to Signature("1234"),
                "bar" to Signature("abcd"),
                "lorem" to null,
            )
            this.writeMap(hashMap)

            this.setDataPosition(0)

            val readMap = mutableMapOf<String, Signature?>()
            this.readMapSk(readMap, Signature::class.java.classLoader)
            assertEquals(hashMap, readMap)
        }
    }

    @Test
    fun readParcelable() {
        withParcel {
            val parcelable = Rect(0, 0, 10, 10)
            this.writeParcelable(parcelable, 0)

            this.setDataPosition(0)
            val readParcelable = this.readParcelableSk<Rect>(Rect::class.java.classLoader)
            assertEquals(parcelable, readParcelable)
        }
    }

    @Test
    @SdkSuppress(minSdkVersion = 30)
    fun readParcelableCreator() {
        withParcel {
            val signature = Signature("1234")
            this.writeParcelableCreator(signature)

            this.setDataPosition(0)
            val readParcelableCreator =
                this.readParcelableCreatorSk<Signature>(Signature::class.java.classLoader)
            assertSame(Signature.CREATOR, readParcelableCreator)
        }
    }

    @Test
    @SdkSuppress(minSdkVersion = 29)
    fun readParcelableList() {
        withParcel {
            val parcelableList = listOf(Signature("1234"), null, Signature("abcd"))
            this.writeParcelableList(parcelableList, 0)

            this.setDataPosition(0)
            val readParcelableList = mutableListOf<Signature?>()
            this.readParcelableListSk(readParcelableList, Signature::class.java.classLoader)
            assertEquals(parcelableList, readParcelableList)
        }
    }

    @Test
    fun readSerializable() {
        withParcel {
            val serializable = "foo"
            this.writeSerializable(serializable)

            this.setDataPosition(0)
            val readSerializable = this.readSerializableSk<String>(String::class.java.classLoader)
            assertEquals(serializable, readSerializable)
        }
    }

    @Test
    fun readSparseArray() {
        withParcel {
            val sparseArray = SparseArray<Signature?>()
            sparseArray[0] = Signature("1234")
            sparseArray[2] = null
            sparseArray[3] = Signature("abcd")
            this.writeSparseArray(sparseArray)

            this.setDataPosition(0)
            val readSparseArray =
                this.readSparseArraySk<Signature?>(Signature::class.java.classLoader)
            assertEquals(sparseArray.size, readSparseArray.size)
            sparseArray.forEach { key, value ->
                assertEquals(value, readSparseArray?.getOrDefault(key, null))
            }
        }
    }
}

private fun withParcel(block: Parcel.() -> Unit) {
    val parcel = Parcel.obtain()
    parcel.block()
    parcel.recycle()
}
