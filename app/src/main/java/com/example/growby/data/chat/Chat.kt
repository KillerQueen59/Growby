package com.example.growby.data.chat

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "chats")
@Parcelize
data class Chat(
        @ColumnInfo(name = "sender")var sender: String,
        @ColumnInfo(name = "text")var text: String,
        @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")var id: Long = 0
):Parcelable
