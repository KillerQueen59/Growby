package com.example.growby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growby.DetailArtikel
import com.example.growby.DetailFood
import com.example.growby.R
import com.example.growby.data.artikel.Artikel
import com.example.growby.data.food.Food
import kotlinx.android.synthetic.main.item_artikel.view.*
import kotlinx.android.synthetic.main.item_food.view.*
import org.jetbrains.anko.startActivity

class ArtikelAdapter(private val artikels: List<Artikel>, private val context: Context): RecyclerView.Adapter<ArtikelViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val view = ArtikelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_artikel,parent,false))
        view.itemView.setOnClickListener {
            val position = view.adapterPosition
            parent.context?.startActivity<DetailArtikel>("artikels" to artikels[position])
        }
        return view
    }

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        holder.bind(artikels[position],context)
    }

    override fun getItemCount(): Int = artikels.size
}

class ArtikelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title = view.title_artikel
    private val photo = view.photo_artikel

    fun bind(artikel: Artikel, context: Context){
        title.text = artikel.title
        Glide.with(context).load(artikel.photo).into(photo)
    }

}