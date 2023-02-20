package com.guideit.commons.exception

import com.guideit.commons.error.ErrorHandler

open class CustomException(
    val title: String = ErrorHandler.DEFAULT_TITLE,
    override val message: String,
    val code: String = ErrorHandler.UNEXPECTED_ERROR
) : Exception()
