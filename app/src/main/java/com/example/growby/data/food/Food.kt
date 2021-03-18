package com.example.growby.data.food

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food (
        var name: String = "",
        var price: Int = 0,
        var detail: String = "",
         var photo: Int = 0,
        var id: Long = 0
        ) : Parcelable