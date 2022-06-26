package com.example.acronymsapp.core.extensions

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideActionBar() = this.supportActionBar?.hide()

fun AppCompatActivity.showActionBar() = this.supportActionBar?.show()