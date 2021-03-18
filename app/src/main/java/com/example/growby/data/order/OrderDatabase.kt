package com.example.growby.data.order

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Order::class],version = 1,exportSchema = false)
abstract class OrderDatabase : RoomDatabase(){

    abstract fun orderDao(): OrderDao
    companion object{
        private var INSTANCE: OrderDatabase? = null

        fun getInstance(context: Context): OrderDatabase?{
            if (INSTANCE == null){
                synchronized(OrderDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    OrderDatabase::class.java,"orderdata.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}