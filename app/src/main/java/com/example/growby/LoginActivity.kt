package com.example.growby

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.growby.SessionManagement.Companion.KEY_DATE
import com.example.growby.SessionManagement.Companion.KEY_EMAIL
import com.example.growby.SessionManagement.Companion.KEY_NAME
import com.example.growby.SessionManagement.Companion.KEY_PASS
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class LoginActivity : AppCompatActivity(){
    lateinit var session: SessionManagement


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        session = SessionManagement(applicationContext)
        if(session.checkLogin()) toMain()
        Log.d("ali","${session.user[KEY_EMAIL]} 1")
        Log.d("ali","${session.user[KEY_PASS]} 2")
        Log.d("ali","${session.user[KEY_NAME]} 3")
        Log.d("ali","${session.user[KEY_DATE]} 4")



        text_daftar.setOnClickListener {
            startActivity<RegisterActivity>()
        }
        btn_masuk.setOnClickListener {
            val email = email_text.text.toString()
            val password = password_text.text.toString()
            var cancel = false
            var focusView: View? = null
            Log.d("ali","${session.user[KEY_EMAIL]} aw")
            Log.d("ali",password)
            if(TextUtils.isEmpty(email)){
                email_text.error = "Bagian ini harus diisi"
                focusView = email_frame
                cancel = true
            }
            else if (email != session.user[KEY_EMAIL]){
                cancel = true
                alert("Daftarkan diri anda dahulu")  {
                    yesButton {  }
                }.show()
            }

            if(TextUtils.isEmpty(password)){
                password_text.error = "Bagian ini harus diisi"
                focusView = password_text
                cancel = true
            } else if (!TextUtils.isEmpty(password) && !(isPasswordValid(password))){
                password_text.error = "Password terlalu pendek"
                focusView = password_text
                cancel = true
            } else if(password != session.user[KEY_PASS]){
                password_text.error = "Password anda salah"
                cancel = true
            }

            if(cancel){
                focusView?.requestFocus()
            } else {
                session.createLoginSession()
                toMain()
            }
        }
    }
    private fun  isPasswordValid(password: String): Boolean{
        return password.length >= 8
    }

    private fun toMain() {
        startActivity<MainActivity>()
        session.createOnBoardSession()
        finish()
    }
}