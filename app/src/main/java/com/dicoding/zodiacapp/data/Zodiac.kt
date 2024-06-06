package com.dicoding.zodiacapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zodiac(
    val name: String,
    val img: Int,
    val birthdate: String,
    val description: String,
    val sign: String
): Parcelable
