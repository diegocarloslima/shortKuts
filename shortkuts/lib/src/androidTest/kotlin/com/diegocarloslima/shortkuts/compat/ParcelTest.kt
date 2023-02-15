package com.diegocarloslima.shortkuts.compat

import android.content.pm.Signature
import android.os.Parcel
import org.junit.Test
import kotlin.test.assertEquals

class ParcelTest {

    @Test
    fun readList() {
        val parcel = Parcel.obtain()

        val list = listOf(Signature("1234"), null, Signature("abcd"))
        parcel.writeList(list)
        parcel.setDataPosition(0)

        val readList = mutableListOf<Signature?>()
        parcel.readListSk(readList, Signature::class.java.classLoader)
        assertEquals(list, readList)
        parcel.recycle()
    }
}
