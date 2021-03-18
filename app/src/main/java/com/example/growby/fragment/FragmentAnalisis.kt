package com.example.growby.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growby.AddEventActivity
import com.example.growby.R
import com.example.growby.adapter.AnalysisAdapter
import com.example.growby.adapter.OrderAdapter
import com.example.growby.data.analysis.Analysis
import com.example.growby.data.analysis.AnalysisDatabase
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.data.order.Order
import com.example.growby.data.order.OrderDatabase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_analysis.view.*
import kotlinx.android.synthetic.main.fragment_order.view.*
import kotlinx.android.synthetic.main.fragment_order.view.rv_orders
import org.jetbrains.anko.support.v4.startActivity
import java.util.ArrayList

class FragmentAnalisis: Fragment() {
    private val analysis : ArrayList<Analysis> = ArrayList()
    private var analysisDatabase: AnalysisDatabase? = null
    val compositeDisposable = CompositeDisposable()
    private val babies : ArrayList<Baby> = ArrayList()
    private var babyDatabase: BabyDatabase? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_analysis,container,false)
        analysisDatabase = AnalysisDatabase.getInstance(requireActivity())
        analysis.addAll(analysisDatabase!!.analysisDao().getAll())
        show(view)
        view.add_event.setOnClickListener {
            startActivity<AddEventActivity>("babies" to babies[0])
            startActivity(Intent( requireActivity(),AddEventActivity::class.java))
        }
        babyDatabase = BabyDatabase.getInstance(requireActivity())
        babies.addAll(babyDatabase!!.babyDao().getAll())
        view.date_first.text = babies[0].date
        view.event_first.text = "${babies[0].name} Lahir"
        return view
    }

    private fun show(view: View){
        view.rv_analysis.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = AnalysisAdapter(analysis,requireContext())
        view.rv_analysis.adapter = adapter
    }
}