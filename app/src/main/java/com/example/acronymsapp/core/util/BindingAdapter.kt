package com.example.acronymsapp.core.util

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import com.example.acronymsapp.R
import com.example.acronymsapp.core.extensions.setResError
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("app:errorRes")
fun setErrorMessage(textInputLayout: TextInputLayout, resError: Int?) {
    textInputLayout.setResError(resError)
    textInputLayout.editText?.addTextChangedListener( object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        }

        override fun afterTextChanged(editable: Editable) {
            if (textInputLayout.editText?.text?.isEmpty() == true) {
                textInputLayout.setResError(resError)
            } else {
                textInputLayout.setResError(null)
            }
        }
    })
}
