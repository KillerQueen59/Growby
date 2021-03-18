package com.example.growby.data.baby

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Baby::class],version = 1,exportSchema = false)
abstract class BabyDatabase : RoomDatabase() {

    abstract fun babyDao(): BabyDao
    companion object{
        private var INSTANCE: BabyDatabase? = null

        fun getInstance(context: Context): BabyDatabase? {
            if(INSTANCE == null){
                synchronized(BabyDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    BabyDatabase::class.java,"babydata.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}