package com.example.growby.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.growby.onboard.*

class OnBoardPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnBoardFragment1()
            }
            1 -> {
                OnBoardFragment2()
            }
            2->{
                OnBoardFragment3()
            }
            3 -> {
                OnBoardFragment4()
            }
            else -> {
                return OnBoardFragment5()
            }
        }
    }
}