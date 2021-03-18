package com.example.growby.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.growby.R
import com.example.growby.data.analysis.Analysis
import kotlinx.android.synthetic.main.add_event_activity.view.*
import kotlinx.android.synthetic.main.item_analysis.view.*
import kotlinx.android.synthetic.main.item_analysis.view.tanggal_event

class AnalysisAdapter(private val analysis: List<Analysis>,private val context: Context): RecyclerView.Adapter<AnalysisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisViewHolder {
        val view = AnalysisViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_analysis,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: AnalysisViewHolder, position: Int) {
        holder.bind(analysis[position],context)
    }

    override fun getItemCount(): Int = analysis.size
}

class AnalysisViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val date = view.tanggal_event
    private val event = view.detail_event

    @SuppressLint("SetTextI18n")
    fun bind(analysis: Analysis, context: Context){
        date.text = analysis.date
        when(analysis.type){
            "Pertumbuhan" -> {
                event.text = analysis.input1 + "\n" + analysis.input2
                event.background = ContextCompat.getDrawable(context,R.drawable.round_green)
            }
            "Sakit" -> {
                event.text = analysis.input1
                event.background = ContextCompat.getDrawable(context,R.drawable.round_red)
            }
            "Imunisasi" -> {
                event.text = analysis.input1
                event.background = ContextCompat.getDrawable(context,R.drawable.round_blue)
            }
            "Vitamin" -> {
                event.text = analysis.input1
                event.background = ContextCompat.getDrawable(context,R.drawable.round_yellow)
            }
            "Mobilitas" -> {
                event.text = analysis.input1
                event.background = ContextCompat.getDrawable(context,R.drawable.round_purple)
            }
        }
    }

}
