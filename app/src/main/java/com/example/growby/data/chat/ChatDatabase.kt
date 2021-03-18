package com.example.growby.data.chat

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.growby.data.baby.BabyDatabase

@Database(entities = [Chat::class],version = 1,exportSchema = false)
abstract class ChatDatabase : RoomDatabase(){

    abstract fun  chatDao(): ChatDao
    companion object{
        private var INSTANCE: ChatDatabase? = null

        fun getInstance(context: Context):ChatDatabase?{
            if (INSTANCE == null){
                synchronized(ChatDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ChatDatabase::class.java,"chatdata.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            ChatDatabase.INSTANCE = null
        }
    }
}