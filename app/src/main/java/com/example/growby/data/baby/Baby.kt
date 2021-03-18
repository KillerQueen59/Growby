package com.example.growby.data.baby

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "babies")
data class Baby(
    @ColumnInfo(name = "name")var name: String,
    @ColumnInfo(name = "age")var age: String,
    @ColumnInfo(name = "date")var date: String,
    @ColumnInfo(name = "parent")var parent: String,
    @ColumnInfo(name = "gender")var gender: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
):Parcelable