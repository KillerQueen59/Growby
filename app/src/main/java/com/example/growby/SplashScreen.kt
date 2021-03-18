package com.example.growby

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.growby.onboard.OnBoardActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

class SplashScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed(Runnable {
            startActivity(intentFor<OnBoardActivity>().noAnimation())
            finish() }, 2000)
    }
}