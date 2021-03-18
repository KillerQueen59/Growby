package com.example.growby.data.analysis

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnalysisDao {

    @Query("SELECT * from analysis")
    fun getAll(): List<Analysis>

    @Insert
    fun  insert(analysis: Analysis)

    @Delete
    fun delete(analysis: Analysis)

    @Query("UPDATE analysis SET baby=:baby,date=:date,type=:type,input1=:input1,input2=:input2 WHERE id=:id")
    fun update(id: Long,baby: String,date:String,type: String,input1:String,input2:String)
}