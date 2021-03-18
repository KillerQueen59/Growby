package com.example.growby.data.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {

    @Query("SELECT * from chats")
    fun getAll(): List<Chat>

    @Insert
    fun insert(chat: Chat)
}