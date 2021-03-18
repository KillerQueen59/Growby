package com.example.growby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growby.DetailFood
import com.example.growby.R
import com.example.growby.data.food.Food
import kotlinx.android.synthetic.main.fragment_makanan.view.*
import kotlinx.android.synthetic.main.item_food.view.*
import org.jetbrains.anko.startActivity

class FoodAdapter(private val foods: List<Food>,private val context: Context): RecyclerView.Adapter<FoodViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = FoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false))
        view.itemView.setOnClickListener {
            val position = view.adapterPosition
            parent.context?.startActivity<DetailFood>("food" to foods[position])
        }
        return view
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position],context)
    }

    override fun getItemCount(): Int = foods.size
}

class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name = view.name_food
    private val detail = view.detail_food
    private val image = view.image_food

    fun bind(food: Food,context: Context){
        name.text = food.name
        detail.text = food.detail
        Glide.with(context).load(food.photo).into(image)
    }

}
