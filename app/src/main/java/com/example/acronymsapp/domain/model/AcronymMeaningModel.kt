package com.example.acronymsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AcronymMeaningModel(
    val lf: String
) : Parcelable