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
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContextTest {

    @Test
    fun startActivity() {
        Intents.init()
        with(ActivityScenario.launch(FirstTestActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity<SecondTestActivity>()
            }
        }
        intended(hasComponent(SecondTestActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun startActivityIntentBlock() {
        Intents.init()
        with(ActivityScenario.launch(FirstTestActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity<SecondTestActivity> {
                    putExtra(TEST_EXTRA, true)
                }
            }
        }
        intended(
            allOf(
                hasComponent(SecondTestActivity::class.java.name),
                hasExtra(TEST_EXTRA, true),
            ),
        )
        Intents.release()
    }

    @Test
    fun startActivityAction() {
        Intents.init()
        with(ActivityScenario.launch(FirstTestActivity::class.java)) {
            onActivity { activity ->
                activity.startActivity(ACTION_TEST)
            }
        }
        intended(hasAction(ACTION_TEST))
        Intents.release()
    }

    @Test
    fun startActivityActionIntentBlock() {
        Intents.init()
        with(ActivityScenario.launch(FirstTestActivity::class.java)) {
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
        Intents.release()
    }
}

private const val TEST_EXTRA = "ContextTestExtra"
private const val ACTION_TEST = "com.diegocarloslima.shortkuts.activity.ACTION_TEST"

class FirstTestActivity : Activity()
class SecondTestActivity : Activity()
