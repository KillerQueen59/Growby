package com.example.growby.data.analysis

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "analysis")
data class Analysis(
    @ColumnInfo(name = "baby") var baby: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "input1") var input1: String,
    @ColumnInfo(name = "input2") var input2: String? = "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Long = 0
):Parcelable
