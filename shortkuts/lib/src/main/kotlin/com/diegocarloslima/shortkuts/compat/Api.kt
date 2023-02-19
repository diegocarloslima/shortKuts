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

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.ChecksSdkIntAtLeast

object Api {

    @ChecksSdkIntAtLeast(api = 34)
    inline fun isAtLeast34(): Boolean = VERSION.SDK_INT >= 34

    @ChecksSdkIntAtLeast(api = VERSION_CODES.TIRAMISU)
    inline fun isAtLeast33(): Boolean = VERSION.SDK_INT >= VERSION_CODES.TIRAMISU

    @ChecksSdkIntAtLeast(api = VERSION_CODES.S_V2)
    inline fun isAtLeast32(): Boolean = VERSION.SDK_INT >= VERSION_CODES.S_V2

    @ChecksSdkIntAtLeast(api = VERSION_CODES.S)
    inline fun isAtLeast31(): Boolean = VERSION.SDK_INT >= VERSION_CODES.S

    @ChecksSdkIntAtLeast(api = VERSION_CODES.R)
    inline fun isAtLeast30(): Boolean = VERSION.SDK_INT >= VERSION_CODES.R

    @ChecksSdkIntAtLeast(api = VERSION_CODES.Q)
    inline fun isAtLeast29(): Boolean = VERSION.SDK_INT >= VERSION_CODES.Q

    @ChecksSdkIntAtLeast(api = VERSION_CODES.P)
    inline fun isAtLeast28(): Boolean = VERSION.SDK_INT >= VERSION_CODES.P

    @ChecksSdkIntAtLeast(api = VERSION_CODES.O_MR1)
    inline fun isAtLeast27(): Boolean = VERSION.SDK_INT >= VERSION_CODES.O_MR1

    @ChecksSdkIntAtLeast(api = VERSION_CODES.O)
    inline fun isAtLeast26(): Boolean = VERSION.SDK_INT >= VERSION_CODES.O

    @ChecksSdkIntAtLeast(api = VERSION_CODES.N_MR1)
    inline fun isAtLeast25(): Boolean = VERSION.SDK_INT >= VERSION_CODES.N_MR1

    @ChecksSdkIntAtLeast(api = VERSION_CODES.N)
    inline fun isAtLeast24(): Boolean = VERSION.SDK_INT >= VERSION_CODES.N

    @ChecksSdkIntAtLeast(api = VERSION_CODES.M)
    inline fun isAtLeast23(): Boolean = VERSION.SDK_INT >= VERSION_CODES.M

    @ChecksSdkIntAtLeast(api = VERSION_CODES.LOLLIPOP_MR1)
    inline fun isAtLeast22(): Boolean = VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP_MR1
}
