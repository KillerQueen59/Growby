package com.example.growby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.growby.SessionManagement.Companion.KEY_DATE
import com.example.growby.SessionManagement.Companion.KEY_NAME
import com.example.growby.data.analysis.AnalysisDatabase
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.data.chat.ChatDatabase
import com.example.growby.data.order.OrderDatabase
import kotlinx.android.synthetic.main.profile_activity.*
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class ProfileActivity: AppCompatActivity(){
    lateinit var session: SessionManagement
    private val babies : ArrayList<Baby> = ArrayList()
    private var babyDatabase: BabyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        session = SessionManagement(applicationContext)
        name_parent.text = session.user[KEY_NAME]
        date_parent.text = session.user[KEY_DATE]
        babyDatabase = BabyDatabase.getInstance(this)
        babies.addAll(babyDatabase!!.babyDao().getAll())
        hubungan_parent.text = babies[0].parent

        btn_back.setOnClickListener {
            finish()
        }
        btn_out.setOnClickListener {
            session.logoutUser()
            startActivity<LoginActivity>()
            finish()
            BabyDatabase.destroyInstance()
            OrderDatabase.destroyInstance()
            AnalysisDatabase.destroyInstance()
            ChatDatabase.destroyInstance()
        }


    }
}