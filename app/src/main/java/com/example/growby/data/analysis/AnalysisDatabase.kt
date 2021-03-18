package com.example.growby.data.analysis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Analysis::class],version = 1,exportSchema = false)
abstract class AnalysisDatabase : RoomDatabase(){

    abstract fun analysisDao(): AnalysisDao
    companion object{
        private var INSTANCE: AnalysisDatabase? = null
        fun getInstance(context: Context):AnalysisDatabase?{
            if (INSTANCE == null){
                synchronized(AnalysisDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AnalysisDatabase::class.java,"analysisdata.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}