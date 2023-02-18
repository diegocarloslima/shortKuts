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
import android.os.Parcel
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import kotlin.test.assertEquals

class ParcelTest {

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
}

private fun withParcel(block: Parcel.() -> Unit) {
    val parcel = Parcel.obtain()
    parcel.block()
    parcel.recycle()
}
