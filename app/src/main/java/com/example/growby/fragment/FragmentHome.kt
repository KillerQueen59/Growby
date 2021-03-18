package com.example.growby.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.growby.ProfileActivity
import com.example.growby.R
import com.example.growby.adapter.PagerAdapter
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.support.v4.startActivity
import java.text.SimpleDateFormat
import java.util.*


class FragmentHome : Fragment() {

    private lateinit var pager: ViewPager
    private lateinit var tabs: TabLayout
    private var dateNow: Long = 0
    private var birthdate: Long = 0

    val compositeDisposable = CompositeDisposable()
    private val babies : ArrayList<Baby> = ArrayList()
    private var babyDatabase: BabyDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        dateNow = Calendar.getInstance().timeInMillis
        babyDatabase = BabyDatabase.getInstance(requireActivity())
        babies.addAll(babyDatabase!!.babyDao().getAll())
        Log.d("anjing", babies.toString())
        view.text_nama_bayi.text = babies[0].name
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val date = sdf.parse(babies[0].age)
        val millis = date.time
        val c = Calendar.getInstance()
        c.timeInMillis = dateNow - millis
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        view.text_umur_bayi.text = "${mYear - 1970} Tahun $mMonth Bulan $mDay Hari"
        clearFragmentStack()
        pager = view.pager
        tabs = view.tabs
        pager.adapter = PagerAdapter(childFragmentManager)
        tabs.setupWithViewPager(pager)
        view.account.setOnClickListener {
            startActivity<ProfileActivity>()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearFragmentStack()
        pager = view.pager
        tabs = view.tabs
        pager.adapter = PagerAdapter(childFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    private fun clearFragmentStack(){
        val fm = this.childFragmentManager
        for ( i in 0..fm.backStackEntryCount){
            fm.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BabyDatabase.destroyInstance()
        compositeDisposable.dispose()
    }

    override fun onResume() {
        super.onResume()
        clearFragmentStack()
        pager.adapter = PagerAdapter(childFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            requireFragmentManager().beginTransaction().detach(this).attach(this).commit()
        }
    }
}