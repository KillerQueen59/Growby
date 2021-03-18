package com.example.growby

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.transition.Fade
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.utils.formatDate
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.add_baby_activity.*
import kotlinx.android.synthetic.main.dialog_pilih_kelamin.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

class AddBabyActivity: AppCompatActivity() {

    lateinit var name: String
    lateinit var date: String
    private var age: String = ""
    lateinit var gender: String
    lateinit var parent: String
    lateinit var popupWindow: PopupWindow
    lateinit var viewModal: View
    lateinit var orderInputDate: LinearLayout
    private var orderDay: Int = 0
    private var orderMonth: Int = 0
    private var orderYear: Int = 0
    private var orderDayString: String = ""
    private var orderMonthString: String = ""
    private var orderYearString: String = ""
    private var orderYearStringApi: String = ""
    private var dateNow : Long = 0
    private var birthDate = ""

    lateinit var session: SessionManagement

    val compositeDisposable = CompositeDisposable()

    private var babyDatabase: BabyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_baby_activity)
        session = SessionManagement(applicationContext)
        babyDatabase = BabyDatabase.getInstance(this)
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //name = nama_bayi_text.text.toString()
        jenis_kelamin_frame.setOnClickListener {
            viewModal = inflater.inflate(R.layout.dialog_pilih_kelamin, null)
            popupWindow = PopupWindow(
                viewModal,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            openChooseKelamin(viewModal)
        }

        val locale = resources.configuration.locale
        Locale.setDefault(locale)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        orderInputDate = tanggal_frame
        orderInputDate.setOnClickListener {
            val date = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, _year, _month, _day ->
                    orderYear = _year
                    orderMonth = _month + 1
                    orderDay = _day

                    orderDayString = if (orderDay <= 9) "0$orderDay"
                    else orderDay.toString()

                    orderMonthString = if (orderMonth <= 9) "0$orderMonth"
                    else orderMonth.toString()

                    orderYearString = orderYear.toString()
                    orderYearStringApi = _year.toString()
                    birthDate = "$orderYearString/$orderMonthString/$orderDayString"
                    age = birthDate

                    //age = "${mYear - 1970} Tahun $mMonth Bulan $mDay Hari"
                   // Log.d("napis", "${mYear - 1970} Tahun $mMonth Bulan $mDay Hari")
                    tanggal_daftar.text = formatDate(
                        orderDayString + "-" + orderMonthString + "-" + orderYearString,
                        this
                    )
                    tanggal_daftar.error = null
                },
                year,
                month,
                day
            )
            date.datePicker.maxDate = System.currentTimeMillis()

            if(orderYear != 0) date.updateDate(orderYear + 1, orderMonth - 1, orderDay)
            date.show()
        }

        dateNow = Calendar.getInstance().timeInMillis
        btn_tambah.setOnClickListener {
            val name = nama_bayi_text.text.toString()
            val hubungan = hubungan_text.text.toString()
            val date = tanggal_daftar.text.toString()
            val ages = age
            val gender = jenis_kelamin_text.text.toString()
            var cancel = false
            if(TextUtils.isEmpty(name)){
                nama_bayi_text.error = "Bagian ini harus diisi dulu"
                cancel = true
            }
            if(TextUtils.isEmpty(hubungan)){
                hubungan_text.error = "Bagian ini harus diisi dulu"
                cancel = true
            }
            if(TextUtils.isEmpty(date)){
                tanggal_daftar.error = "Bagian ini harus diisi dulu"
                cancel = true
            }
            if(TextUtils.isEmpty(gender)){
                jenis_kelamin_text.error = "Bagian ini harus diisi dulu"
                cancel = true
            }
            if(!cancel){
                insertToDb(Baby(name,ages,date,hubungan,gender))
                toast("Data Berhasil Di tambahkan")
                startActivity<MainActivity>()
                session.addBaby()
                finish()
            }
        }
        Log.d("napis", dateNow.toString())
        Log.d("napis", tanggal_daftar.text.toString())
    }
    fun insertToDb(baby: Baby){
        compositeDisposable.add(Completable.fromRunnable { babyDatabase?.babyDao()?.insert(baby) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            },{
                Log.d("anjing","gagal")
            }))
    }



    @SuppressLint("NewApi")
    private fun openChooseKelamin(viem: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                val fadeIn = Fade()
                popupWindow.enterTransition = fadeIn

                val fadeOut= Fade()
                popupWindow.exitTransition = fadeOut
            }
            val boy = viem.frame_boy
            val girl = viem.frame_girl
            var choose = 0
            val pilih = viem.btn_choose
            boy.setOnClickListener {
                boy.setBackgroundColor(ContextCompat.getColor(this, R.color.bluelight))
                girl.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                choose = 2
            }
            girl.setOnClickListener {
                boy.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                girl.setBackgroundColor(ContextCompat.getColor(this, R.color.redlight))
                choose = 1
            }
            pilih.setOnClickListener {
                if(choose>0){
                    when(choose){
                        1 -> gender = "Perempuan"
                        2 -> gender = "Laki - Laki"
                    }
                    jenis_kelamin_text.text = gender
                    popupWindow.dismiss()
                } else {
                    toast("Pilih Kelamin terlebih dahulu")
                }
            }
            popupWindow.setOnDismissListener {
            }
            // Finally, show the popup window on app
        TransitionManager.beginDelayedTransition(roots)
        popupWindow.showAtLocation(
            roots, // Location to display popup window
            Gravity.CENTER, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )
        }

}