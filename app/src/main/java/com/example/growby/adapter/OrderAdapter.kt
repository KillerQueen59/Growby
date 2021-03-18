package com.example.growby.adapter

import android.content.Context
import android.graphics.Color.green
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growby.R
import com.example.growby.data.order.Order
import kotlinx.android.synthetic.main.item_orders.view.*

class OrderAdapter(private val orders: List<Order>,private val context: Context): RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_orders,parent,false))
        view.itemView.setOnClickListener {
            val position = view.adapterPosition

        }
        return view
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position],context)
    }

    override fun getItemCount(): Int = orders.size
}

class OrderViewHolder(view:View): RecyclerView.ViewHolder(view){
    private val name = view.food_name
    private val count = view.count_food
    private val price = view.price_food
    private val status = view.status
    private val image = view.food_photo

    fun bind(order:Order,context: Context){
        name.text = order.food
        count.text = "${order.count} Item"
        price.text = order.price
        when(order.status){
            true -> {
                status.text = "Selesai"
                status.setTextColor(ContextCompat.getColor(context,R.color.green))
            }
            false -> {
                status.text = "Sedang\nDiantar"
                status.setTextColor(ContextCompat.getColor(context,R.color.red))
            }
        }
        Glide.with(context).load(order.photo).into(image)
    }
}
