package com.example.growby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.growby.R.id.*
import com.example.growby.fragment.FragmentConsul
import com.example.growby.fragment.FragmentHome
import com.example.growby.fragment.FragmentOrder

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    lateinit var session: SessionManagement
    private val homeFragment: Fragment = FragmentHome()
    private val consulFragment: Fragment = FragmentConsul()
    private val orderFragment: Fragment = FragmentOrder()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        session = SessionManagement(applicationContext)
        Log.d("ojan",session.checkEmpty().toString())
        if(session.checkEmpty()) addBaby()
        commitFragment(homeFragment)
        navigation.setOnNavigationItemSelectedListener {
            clearFragmentStack()
            when(it.itemId){
                nav_home -> {
                    commitFragment(homeFragment)
                }
                nav_concul -> {
                    commitFragment(consulFragment)
                }
                nav_order -> {
                    commitFragment(orderFragment)
                }
            }
            true
        }
    }

    private fun addBaby() {
        startActivity<AddBabyActivity>()
        finish()
    }


    private fun commitFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun clearFragmentStack(){
        val fm = this.supportFragmentManager
        for ( i in 0..fm.backStackEntryCount){
            fm.popBackStack()
        }
    }


    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}