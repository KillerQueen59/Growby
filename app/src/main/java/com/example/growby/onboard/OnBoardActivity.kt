package com.example.growby.onboard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.growby.LoginActivity
import com.example.growby.R
import com.example.growby.SessionManagement
import com.example.growby.adapter.OnBoardPageAdapter
import kotlinx.android.synthetic.main.onboard_activity.*
import org.jetbrains.anko.startActivity

class OnBoardActivity : AppCompatActivity(){
    private lateinit var pager: ViewPager
    private var currentPage = 0
    private lateinit var session: SessionManagement



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard_activity)
        session = SessionManagement(applicationContext)
        if(session.checkFirst()) toLogin()
        pager = container_onboarding
        pager.adapter = OnBoardPageAdapter(supportFragmentManager)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        indicator_0.setBackgroundResource(R.drawable.onboarding_indicator_selected)
                        indicator_1.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_2.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_3.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_4.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        btn_prev.visibility = View.INVISIBLE
                        currentPage = 0
                    }
                    1 -> {
                        indicator_0.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_1.setBackgroundResource(R.drawable.onboarding_indicator_selected)
                        indicator_2.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_2.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_3.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_4.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        btn_finish.visibility = View.INVISIBLE
                        btn_next.visibility = View.VISIBLE
                        btn_prev.visibility = View.VISIBLE
                        currentPage = 1
                    }
                    2 -> {
                        indicator_0.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_1.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_2.setBackgroundResource(R.drawable.onboarding_indicator_selected)
                        indicator_3.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_4.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        btn_finish.visibility = View.INVISIBLE
                        btn_next.visibility = View.VISIBLE
                        btn_prev.visibility = View.VISIBLE
                        currentPage = 2
                    }
                    3 ->{
                        indicator_0.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_1.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_2.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_3.setBackgroundResource(R.drawable.onboarding_indicator_selected)
                        indicator_4.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        btn_finish.visibility = View.INVISIBLE
                        btn_next.visibility = View.VISIBLE
                        btn_prev.visibility = View.VISIBLE
                        currentPage = 3
                    }
                    4 -> {
                        indicator_0.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_1.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_2.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_3.setBackgroundResource(R.drawable.onboarding_indicator_unselected)
                        indicator_4.setBackgroundResource(R.drawable.onboarding_indicator_selected)
                        btn_finish.visibility = View.VISIBLE
                        btn_next.visibility = View.INVISIBLE
                        btn_prev.visibility = View.VISIBLE
                        currentPage = 4
                    }
                }
            }
        })

        btn_next.setOnClickListener {
            currentPage++
            pager.setCurrentItem(currentPage,true)
            Log.d("ojan",currentPage.toString())
        }
        btn_prev.setOnClickListener {
            currentPage--
            pager.setCurrentItem(currentPage,true)
        }
        btn_finish.setOnClickListener {
            toLogin()
        }

    }

    fun toLogin() {
        startActivity<LoginActivity>()
        //session.createOnBoardSession()
        finish()
    }

}