package com.example.sw888_p2

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    @DrawableRes val imageResId: Int? = null
) : Parcelable
