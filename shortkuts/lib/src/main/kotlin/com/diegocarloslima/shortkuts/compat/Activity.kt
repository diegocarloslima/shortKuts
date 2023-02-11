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

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.Size
import androidx.core.app.ActivityCompat
import androidx.core.content.LocusIdCompat
import androidx.core.view.DragAndDropPermissionsCompat

/**
 * Uses [ActivityCompat] to obtain information about who launched this activity.
 *
 * @see ActivityCompat.getReferrer
 */
inline val Activity.referrerSk: Uri? get() = ActivityCompat.getReferrer(this)

/**
 * Uses [ActivityCompat] to indicate whether this activity is launched from a bubble.
 *
 * @return `true` if this activity is launched from a bubble.
 *
 * @see ActivityCompat.isLaunchedFromBubble
 */
inline val Activity.isLaunchedFromBubbleSk: Boolean
    get() = ActivityCompat.isLaunchedFromBubble(this)

/**
 * Uses [ActivityCompat] to recreate the activity with a new instance
 *
 * @see ActivityCompat.recreate
 */
inline fun Activity.recreateSk() = ActivityCompat.recreate(this)

/**
 * Uses [ActivityCompat] to create a [DragAndDropPermissionsCompat] bound to this activity and
 * controlling the access permissions for content URIs associated with the [DragEvent].
 *
 * @param dragEvent Drag event to request permission for
 *
 * @return The [DragAndDropPermissionsCompat] object used to control access to the content
 * URIs. `null` if no content URIs are associated with the event or if permissions could
 * not be granted
 *
 * @see ActivityCompat.requestDragAndDropPermissions
 *
 */
inline fun Activity.requestDragAndDropPermissionsSk(
    dragEvent: DragEvent,
): DragAndDropPermissionsCompat? =
    ActivityCompat.requestDragAndDropPermissions(this, dragEvent)

/**
 * Uses [ActivityCompat] to request permissions to be granted to this application.
 *
 * @param permissions The requested permissions. Must be not empty.
 * @param requestCode Application specific request code to match with a result. Should be >= 0
 *
 * @throws IllegalArgumentException if the request contains invalid or empty values.
 *
 * @see ActivityCompat.requestPermissions
 *
 */
@Throws(IllegalArgumentException::class)
inline fun Activity.requestPermissionsSk(
    @Size(min = 1) permissions: List<String>,
    @IntRange(from = 0) requestCode: Int,
) = ActivityCompat.requestPermissions(this, permissions.toTypedArray(), requestCode)

/**
 * Uses [ActivityCompat] to find a view that was identified by the `android:id` XML attribute that
 * was processed in [Activity.onCreate].
 *
 * @param id the ID to search for
 *
 * @return a view [T] with given ID
 *
 * @throws IllegalArgumentException if the ID is invalid, or there is no matching view in the
 * hierarchy.
 *
 * @see ActivityCompat.requireViewById
 */
@Throws(IllegalArgumentException::class)
inline fun <T : View> Activity.requireViewByIdSk(@IdRes id: Int): T =
    ActivityCompat.requireViewById(this, id)

/**
 * Uses [ActivityCompat] to set the [LocusIdCompat] for this activity. The locus id helps identify
 * different instances of the same [Activity] class.
 *
 * @param locusId  a unique, stable id that identifies this [Activity] instance.
 *
 * @param bundle extras set or updated as part of this locus context.
 *
 * @see ActivityCompat.setLocusContext
 */
inline fun Activity.setLocusContextSk(locusId: LocusIdCompat? = null, bundle: Bundle? = null) =
    ActivityCompat.setLocusContext(this, locusId, bundle)

/**
 * Uses [ActivityCompat] to know whether you should show UI with rationale before requesting a
 * permission.
 *
 *  @param permission A permission your app wants to request.
 *
 *  @see ActivityCompat.shouldShowRequestPermissionRationale
 */
inline fun Activity.shouldShowRequestPermissionRationaleSk(permission: String): Boolean =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
