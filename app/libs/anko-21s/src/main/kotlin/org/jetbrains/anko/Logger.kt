/*
 * Copyright 2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.anko

import android.content.Context
import android.app.Fragment
import android.util.Log

public trait AnkoLogger {
    protected val loggerTag: String
        get() = javaClass.getSimpleName()

    protected final fun verbose(message: Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.VERBOSE)) Log.v(tag, message?.toString() ?: "null")
    }

    protected final fun debug(message: Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.DEBUG)) Log.d(tag, message?.toString() ?: "null")
    }

    protected final fun info(message: Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.INFO)) Log.i(tag, message?.toString() ?: "null")
    }

    protected final fun warn(message: Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.WARN)) Log.w(tag, message?.toString() ?: "null")
    }

    protected final fun error(message: Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.ERROR)) Log.e(tag, message?.toString() ?: "null")
    }

    protected final fun wtf(message: Any?) {
        Log.v(loggerTag, message?.toString() ?: "null")
    }

    /* lazy */

    protected final inline fun verbose(message: () -> Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.VERBOSE)) Log.v(tag, message()?.toString() ?: "null")
    }

    protected final inline fun debug(message: () -> Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.DEBUG)) Log.d(tag, message()?.toString() ?: "null")
    }

    protected final inline fun info(message: () -> Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.INFO)) Log.i(tag, message()?.toString() ?: "null")
    }

    protected final inline fun warn(message: () -> Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.WARN)) Log.w(tag, message()?.toString() ?: "null")
    }

    protected final inline fun error(message: () -> Any?) {
        val tag = loggerTag
        if (Log.isLoggable(tag, Log.ERROR)) Log.e(tag, message()?.toString() ?: "null")
    }

    protected final inline fun wtf(message: () -> Any?) {
        Log.v(loggerTag, message()?.toString() ?: "null")
    }

}

public fun Throwable.getStackTraceString(): String = Log.getStackTraceString(this)
