package com.example.growby.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.growby.fragment.FragmentAnalisis
import com.example.growby.fragment.FragmentArtikel
import com.example.growby.fragment.FragmentMakanan
import com.example.growby.fragment.FragmentReward


class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){


    private val pages = listOf(
            FragmentMakanan(),
            FragmentAnalisis(),
            FragmentArtikel(),
            FragmentReward()
    )

    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }



    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Makanan"
            1 -> "Analisis"
            2 -> "Artikel"
            else -> "Reward"
        }
    }
}