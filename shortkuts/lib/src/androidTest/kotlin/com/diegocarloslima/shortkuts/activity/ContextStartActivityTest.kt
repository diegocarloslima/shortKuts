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

package com.diegocarloslima.shortkuts.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ContextStartActivityTest {

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun teardown() {
        Intents.release()
    }

    @Test
    fun startActivity() {
        with(ActivityScenario.launch(FirstContextStartActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity<SecondContextStartActivity>()
            }
        }
        intended(hasComponent(SecondContextStartActivity::class.java.name))
    }

    @Test
    fun startActivityIntentBlock() {
        with(ActivityScenario.launch(FirstContextStartActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity<SecondContextStartActivity> {
                    putExtra(TEST_EXTRA, true)
                }
            }
        }
        intended(
            allOf(
                hasComponent(SecondContextStartActivity::class.java.name),
                hasExtra(TEST_EXTRA, true),
            ),
        )
    }

    @Test(expected = ActivityNotFoundException::class)
    fun startActivityUnregistered() {
        with(ActivityScenario.launch(FirstContextStartActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity<UnregisteredContextStartActivity>()
            }
        }
    }

    @Test
    fun startActivityAction() {
        with(ActivityScenario.launch(FirstContextStartActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity(ACTION_TEST)
            }
        }
        intended(hasAction(ACTION_TEST))
    }

    @Test
    fun startActivityActionIntentBlock() {
        with(ActivityScenario.launch(FirstContextStartActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity(ACTION_TEST) {
                    putExtra(TEST_EXTRA, true)
                }
            }
        }
        intended(
            allOf(
                hasAction(ACTION_TEST),
                hasExtra(TEST_EXTRA, true),
            ),
        )
    }

    @Test(expected = ActivityNotFoundException::class)
    fun startActivityInvalidAction() {
        with(ActivityScenario.launch(FirstContextStartActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity(INVALID_ACTION)
            }
        }
    }
}

private const val TEST_EXTRA = "ContextStartActivity"
private const val ACTION_TEST = "com.diegocarloslima.shortkuts.activity.ACTION_TEST"
private const val INVALID_ACTION = "com.diegocarloslima.shortkuts.activity.INVALID"

class FirstContextStartActivity : Activity()
class SecondContextStartActivity : Activity()
class UnregisteredContextStartActivity : Activity()
