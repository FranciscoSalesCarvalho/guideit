package com.guideit.dsc.extensions

import androidx.core.text.HtmlCompat

fun String.toHtml() =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)