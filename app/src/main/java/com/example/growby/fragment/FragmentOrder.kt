package com.example.growby.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growby.R
import com.example.growby.adapter.OrderAdapter
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.data.order.Order
import com.example.growby.data.order.OrderDatabase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_order.view.*
import java.util.ArrayList

class FragmentOrder: Fragment() {
    val compositeDisposable = CompositeDisposable()
    private val orders : ArrayList<Order> = ArrayList()
    private var orderDatabase: OrderDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order,container,false)
        orderDatabase = OrderDatabase.getInstance(requireActivity())
        orders.clear()
        orders.addAll(orderDatabase!!.orderDao().getAll())
        Log.d("nendra",orders.toString())
        show(view)
        return view
    }
    private fun show(view: View){
        view.rv_orders.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = OrderAdapter(orders,requireContext())
        view.rv_orders.adapter = adapter
    }
}