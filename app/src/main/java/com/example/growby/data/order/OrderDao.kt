package com.example.growby.data.order

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {

    @Query("SELECT * from orders")
    fun getAll(): List<Order>

    @Insert
    fun insert(order: Order)

    @Delete
    fun delete(order: Order)

    @Query("UPDATE orders SET food=:food,count=:count,address=:address,price=:price,status=:status,photo=:photo WHERE id=:id")
    fun update(id:Long,food:String,count: Int,address: String,price: String,status: Boolean,photo: Int)
}