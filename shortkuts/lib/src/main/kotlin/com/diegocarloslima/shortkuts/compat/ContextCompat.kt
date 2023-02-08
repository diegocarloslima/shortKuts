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

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Resources.NotFoundException
import android.os.Handler
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.RegisterReceiverFlags
import java.io.File
import java.util.concurrent.Executor

/**
 * Uses [ContextCompat] to retrieve the attribution tag for this context.
 *
 * @return the attribution tag this context is for or `null` if this is the default.
 *
 * @see ContextCompat.getAttributionTag
 */
inline val Context.attributionTagSk: String? get() = ContextCompat.getAttributionTag(this)

/**
 * Uses [ContextCompat] to obtain the directory on the filesystem where all private files belonging
 * to this app are stored.
 *
 * @return The [File] representing the data dir or `null` if it was not possible to obtain the
 * absolute path for it
 *
 * @see ContextCompat.getDataDir
 */
inline val Context.dataDirSk: File? get() = ContextCompat.getDataDir(this)

/**
 * Uses [ContextCompat] to obtain an [Executor] to run enqueued tasks on the main thread.
 *
 * @return The [Executor] that will run enqueued tasks on the main thread associated with this
 * context.
 *
 * @see ContextCompat.getMainExecutor
 */
inline val Context.mainExecutorSk: Executor get() = ContextCompat.getMainExecutor(this)

/**
 * Uses [ContextCompat] to obtain information about the protection of the device storage.
 *
 * @return `true` if the storage APIs of this Context are backed by device-encrypted storage.
 *
 * @see ContextCompat.isDeviceProtectedStorage
 */
inline val Context.isDeviceProtectedStorageSk: Boolean
    get() = ContextCompat.isDeviceProtectedStorage(this)

/**
 * Convenience function that uses [ContextCompat] to obtain the name of the system-level service
 * from [S].
 *
 * @return The service name or `null` if the class is not a supported system service.
 *
 * @see ContextCompat.getSystemServiceName
 */
inline fun <reified S : Any> Context.getSystemServiceName(): String? =
    ContextCompat.getSystemServiceName(this, S::class.java)

/**
 * Convenience function that uses [ContextCompat] to register a broadcast receiver.
 *
 * @param filter Selects the [Intent] broadcasts to be received.
 * @param flags Should be either [ContextCompat.RECEIVER_EXPORTED] or
 * [ContextCompat.RECEIVER_NOT_EXPORTED].
 * @param receiver The [BroadcastReceiver] to handle the broadcast.
 * @param broadcastPermission A permission that a broadcaster must hold in order to send an Intent.
 * @param scheduler Handler identifying the thread will receive the Intent.
 *
 * @return The first sticky intent found that matches [filter], or null if there are none.
 *
 * @see ContextCompat.registerReceiver
 */
inline fun Context.registerReceiver(
    filter: IntentFilter,
    @RegisterReceiverFlags flags: Int,
    receiver: BroadcastReceiver? = null,
    broadcastPermission: String? = null,
    scheduler: Handler? = null,
): Intent? =
    ContextCompat.registerReceiver(this, receiver, filter, broadcastPermission, scheduler, flags)

/**
 * Uses [ContextCompat] to determine whether this context has been granted a particular permission.
 *
 * @param permission The name of the permission being checked.
 *
 * @return [PackageManager.PERMISSION_GRANTED] if you have the permission, or
 * [PackageManager.PERMISSION_DENIED] if not.
 *
 * @see ContextCompat.checkSelfPermission
 */
inline fun Context.checkSelfPermissionSk(permission: String): Int =
    ContextCompat.checkSelfPermission(this, permission)

/**
 * Uses [ContextCompat] to obtain a new [Context] object for the current context, but whose storage
 * APIs are backed by device-protected storage.
 *
 * @return new [Context] or `null` if device-protected storage is not available.
 *
 * @see ContextCompat.createDeviceProtectedStorageContext
 */
inline fun Context.createDeviceProtectedStorageContextSk(): Context? =
    ContextCompat.createDeviceProtectedStorageContext(this)

/**
 * Uses [ContextCompat] to obtain a color associated with a particular resource ID.
 *
 * @param id The desired resource identifier, as generated by the aapt tool.
 *
 * @return A single color value in the form 0xAARRGGBB.
 *
 * @throws NotFoundException if the given ID does not exist.
 *
 * @see ContextCompat.getColor
 */
@Throws(NotFoundException::class)
@ColorInt
inline fun Context.getColorSk(@ColorRes id: Int): Int =
    ContextCompat.getColor(this, id)

/**
 * Uses [ContextCompat] to obtain a color state list associated with a particular resource ID.
 *
 * @param id The desired resource identifier, as generated by the aapt tool.
 *
 * @return A color state list, or `null` if the resource could not be resolved.
 *
 * @throws NotFoundException if the given ID does not exist.
 *
 * @see ContextCompat.getColorStateList
 */
@Throws(NotFoundException::class)
inline fun Context.getColorStateListSk(@ColorRes id: Int): ColorStateList? =
    ContextCompat.getColorStateList(this, id)

/**
 * Uses [ContextCompat] to start a foreground service.
 *
 * @param intent The description of the Service to start.
 *
 * @see ContextCompat.startForegroundService
 */
inline fun Context.startForegroundServiceSk(intent: Intent) =
    ContextCompat.startForegroundService(this, intent)
