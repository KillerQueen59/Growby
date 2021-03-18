package com.example.growby.data.baby

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BabyDao {

    @Query("SELECT * from babies")
    fun getAll(): List<Baby>

    @Insert
    fun insert(baby: Baby)

    @Delete
    fun delete(baby: Baby)

    @Query("UPDATE babies SET name=:name,age=:age,date=:date,parent=:parent,gender=:gender WHERE id =:id")
    fun update(id: Long,name: String,age:String,date:String,parent: String,gender:String)
}