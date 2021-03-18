package com.example.growby.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.growby.ChatDetailActivity
import com.example.growby.R
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import kotlinx.android.synthetic.main.fragment_consult.view.*
import org.jetbrains.anko.support.v4.startActivity
import java.util.ArrayList


class FragmentConsul: Fragment() {
    private val babies : ArrayList<Baby> = ArrayList()
    private var babyDatabase: BabyDatabase? = null
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_consult, container, false)
        babyDatabase = BabyDatabase.getInstance(requireActivity())
        babies.addAll(babyDatabase!!.babyDao().getAll())
        view.name_parent.text = "${babies[0].parent} ${babies[0].name}"
            view.doctor1_layout.setOnClickListener {
                startActivity<ChatDetailActivity>()
            }

        return view
    }
}