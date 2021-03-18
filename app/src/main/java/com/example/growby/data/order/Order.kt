package com.example.growby.data.order

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "orders")
data class Order(
    @ColumnInfo(name = "food") var food:String,
    @ColumnInfo(name = "count") var count: Int,
    @ColumnInfo(name = "photo") var photo: Int,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "price") var price: String,
    @ColumnInfo(name = "status") var status: Boolean = false,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
):Parcelable
