package com.example.growby.data.artikel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artikel(
    var title: String = "",
    var details: String = "",
    var date: String = "",
    var source: String = "",
    var photo: Int = 0,
): Parcelable
