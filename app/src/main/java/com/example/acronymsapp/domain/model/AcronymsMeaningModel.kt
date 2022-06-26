package com.example.acronymsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AcronymsMeaningModel(
    val sf: String,
    val lfs: List<AcronymMeaningModel>
) : Parcelable