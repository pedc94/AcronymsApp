package com.example.acronymsapp.core.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setResError(resError: Int?) {
    resError?.let {
        setError(context.resources.getString(it))
    } ?: setError(null)
}