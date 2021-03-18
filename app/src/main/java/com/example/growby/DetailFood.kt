package com.example.growby

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.growby.data.food.Food
import kotlinx.android.synthetic.main.activity_food_detail.*
import org.jetbrains.anko.startActivity

class DetailFood: AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)
        val data: Food? = intent.getParcelableExtra("food")
        title_text.text = data!!.name
        price_text.text = "Rp.${data.price}"
        detail_text.text = data.detail
        Glide.with(this).load(data.photo).into(image_food)
        btn_next.setOnClickListener {
            startActivity<OrderActivity>("food" to data)
        }
        back.setOnClickListener {
            finish()
        }
    }
}