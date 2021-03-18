package com.example.growby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.growby.data.artikel.Artikel
import kotlinx.android.synthetic.main.activity_detail_artikel.*

class DetailArtikel: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artikel)
        val data: Artikel = intent.getParcelableExtra("artikels")!!
        title_detail.text = data.title
        date_detail.text = data.date
        source_detail.text = data.source
        detail_detail.text = data.details
        Glide.with(this).load(data.photo).into(photo_detail)
        back.setOnClickListener {
            finish()
        }
    }
}