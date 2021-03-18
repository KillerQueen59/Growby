package com.example.growby

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.growby.utils.formatDate
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.register_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.yesButton
import java.util.*

class RegisterActivity:AppCompatActivity() {
    lateinit var session: SessionManagement

    private var orderDay: Int = 0
    private var orderMonth: Int = 0
    private var orderYear: Int = 0
    private var orderDayString: String = ""
    private var orderMonthString: String = ""
    private var orderYearString: String = ""
    private var orderYearStringApi: String = ""
    lateinit var orderInputDate: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        session = SessionManagement(applicationContext)




        val locale = resources.configuration.locale
        Locale.setDefault(locale)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        orderInputDate = tanggal_frame
        orderInputDate.setOnClickListener {
            val date = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, _year, _month, _day ->
                orderYear = _year
                orderMonth = _month + 1
                orderDay = _day

                orderDayString = if (orderDay <= 9) "0$orderDay"
                else orderDay.toString()

                orderMonthString = if (orderMonth <= 9) "0$orderMonth"
                else orderMonth.toString()

                orderYearString = orderYear.toString()
                orderYearStringApi = _year.toString()

                tanggal_daftar.text = formatDate(orderDayString + "-" + orderMonthString + "-" + orderYearString, this)
                tanggal_daftar.error = null
            }, year, month, day)
            date.datePicker.maxDate = System.currentTimeMillis()
            if(orderYear != 0) date.updateDate(orderYear + 1, orderMonth - 1, orderDay)
            date.show()
        }

        btn_daftar.setOnClickListener {
            val email = email_daftar.text.toString()
            val password = password_daftar.text.toString()
            val name = name_daftar.text.toString()
            val date = tanggal_daftar.text.toString()
            var cancel = false
            var focusView: View? = null

            if (TextUtils.isEmpty(email)) {
                email_daftar.error = "Bagian ini harus diisi"
                focusView = email_daftar
                cancel = true
            }
            if (TextUtils.isEmpty(name)) {
                name_daftar.error = "Bagian ini harus diisi"
                focusView = name_daftar
                cancel = true
            }
            if (TextUtils.isEmpty(password)) {
                password_daftar.error = "Bagian ini harus diisi"
                focusView = password_daftar
                cancel = true
            } else if(!TextUtils.isEmpty(password) && !(isPasswordValid(password))){
                password_daftar.error = "Password terlalu pendek"
                focusView = password_daftar
                cancel = true
            }
            if (TextUtils.isEmpty(date)){
                tanggal_daftar.error = "Bagian ini harus diisi"
                cancel = true
            }
            if(cancel){
                focusView?.requestFocus()
            } else {
                session.createUser(email,password,name,date)
                alert("Berhasil membuat akun") {
                    yesButton {
                        finish()
                    }
                }.show()
            }
        }
    }
    private fun  isPasswordValid(password: String): Boolean{
        return password.length >= 8
    }
}