package com.example.data.exception

import com.example.data.R

data class GenericExceptionModel (
    val messageResource: Int = R.string.exception_generic
) : Exception("")