package com.example.growby.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growby.R
import com.example.growby.adapter.FoodAdapter
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.data.food.Food
import com.example.growby.data.food.FoodData
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_makanan.view.*

class FragmentMakanan: Fragment() {
    private var list: ArrayList<Food> = arrayListOf()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_makanan, container, false)
        view.rv_food.setHasFixedSize(true)
        list.addAll(FoodData.listFood)
        show(view)

        return view
    }

    private fun show(view: View){
        view.rv_food.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = FoodAdapter(list,requireContext())
        view.rv_food.adapter = adapter
    }

}