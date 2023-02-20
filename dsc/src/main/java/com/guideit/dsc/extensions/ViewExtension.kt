package com.guideit.dsc.extensions

import android.view.View
import androidx.annotation.IdRes

fun <V : View> View.findView(@IdRes id: Int): Lazy<V> = lazy<V> { findViewById(id) }