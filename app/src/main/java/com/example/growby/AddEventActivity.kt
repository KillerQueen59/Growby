package com.example.growby

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.growby.data.analysis.Analysis
import com.example.growby.data.analysis.AnalysisDatabase
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.data.order.Order
import com.example.growby.data.order.OrderDatabase
import com.example.growby.utils.formatDate
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.add_event_activity.*
import kotlinx.android.synthetic.main.add_event_activity.tanggal_frame
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

class AddEventActivity: AppCompatActivity() {
    var type = 0
    private var orderDay: Int = 0
    private var orderMonth: Int = 0
    private var orderYear: Int = 0
    private var orderDayString: String = ""
    private var orderMonthString: String = ""
    private var orderYearString: String = ""
    private var orderYearStringApi: String = ""
    lateinit var orderInputDate: LinearLayout
    private lateinit var baby: Baby


    val compositeDisposable = CompositeDisposable()
    private var analysisDatabase: AnalysisDatabase? = null
    private val babies : ArrayList<Baby> = ArrayList()
    private var babyDatabase: BabyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_activity)
        analysisDatabase = AnalysisDatabase.getInstance(this)
        babyDatabase = BabyDatabase.getInstance(this)
        babies.clear()
        babies.addAll(babyDatabase!!.babyDao().getAll())
        init()
        onClick()
    }

    private fun init() {
        image_pertumbuhan.background = ContextCompat.getDrawable(this, R.drawable.round_green)
        text_pertumbuhan.setTextColor(ContextCompat.getColor(this,R.color.black))
        image_sakit.background = ContextCompat.getDrawable(this, R.drawable.round)
        text_sakit.setTextColor(ContextCompat.getColor(this,R.color.grey))
        image_imunisasi.background = ContextCompat.getDrawable(this, R.drawable.round)
        text_imunisasi.setTextColor(ContextCompat.getColor(this,R.color.grey))
        image_vitamin.background = ContextCompat.getDrawable(this, R.drawable.round)
        text_vitamin.setTextColor(ContextCompat.getColor(this,R.color.grey))
        image_mobilitas.background = ContextCompat.getDrawable(this, R.drawable.round)
        text_mobilitas.setTextColor(ContextCompat.getColor(this,R.color.grey))
        input1.hint = "Berat Badan"
        input2.hint = "Tinggi Badan"
        type = 1
    }

    @SuppressLint("SetTextI18n")
    private fun onClick() {
        back.setOnClickListener {
            finish()
        }
        pertumbuhan.setOnClickListener {
            image_pertumbuhan.background = ContextCompat.getDrawable(this, R.drawable.round_green)
            text_pertumbuhan.setTextColor(ContextCompat.getColor(this,R.color.black))
            image_sakit.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_sakit.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_imunisasi.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_imunisasi.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_vitamin.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_vitamin.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_mobilitas.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_mobilitas.setTextColor(ContextCompat.getColor(this,R.color.grey))
            input1.hint = "Berat Badan"
            input2.hint = "Tinggi Badan"
            type = 1
        }
        sakit.setOnClickListener {
            image_pertumbuhan.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_pertumbuhan.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_sakit.background = ContextCompat.getDrawable(this, R.drawable.round_red)
            text_sakit.setTextColor(ContextCompat.getColor(this,R.color.black))
            image_imunisasi.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_imunisasi.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_vitamin.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_vitamin.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_mobilitas.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_mobilitas.setTextColor(ContextCompat.getColor(this,R.color.grey))
            input1.hint = "Gejala"
            input_2.visibility = View.GONE
            type = 2
        }
        imunisasi.setOnClickListener {
            image_pertumbuhan.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_pertumbuhan.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_sakit.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_sakit.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_imunisasi.background = ContextCompat.getDrawable(this, R.drawable.round_blue)
            text_imunisasi.setTextColor(ContextCompat.getColor(this,R.color.black))
            image_vitamin.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_vitamin.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_mobilitas.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_mobilitas.setTextColor(ContextCompat.getColor(this,R.color.grey))
            input1.hint = "Suntik Imunisasi"
            input_2.visibility = View.GONE
            type = 3
        }
        vitamin.setOnClickListener {
            image_pertumbuhan.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_pertumbuhan.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_sakit.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_sakit.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_imunisasi.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_imunisasi.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_vitamin.background = ContextCompat.getDrawable(this, R.drawable.round_yellow)
            text_vitamin.setTextColor(ContextCompat.getColor(this,R.color.black))
            image_mobilitas.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_mobilitas.setTextColor(ContextCompat.getColor(this,R.color.grey))
            input1.hint = "Pemberian Vitamin"
            input_2.visibility = View.GONE
            type = 4
        }
        mobilitas.setOnClickListener {
            image_pertumbuhan.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_pertumbuhan.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_sakit.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_sakit.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_imunisasi.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_imunisasi.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_vitamin.background = ContextCompat.getDrawable(this, R.drawable.round)
            text_vitamin.setTextColor(ContextCompat.getColor(this,R.color.grey))
            image_mobilitas.background = ContextCompat.getDrawable(this, R.drawable.round_purple)
            text_mobilitas.setTextColor(ContextCompat.getColor(this,R.color.black))
            input1.hint = "Tempat Tujuan"
            input_2.visibility = View.GONE
            type = 5
        }
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

                tanggal_event.text = formatDate(orderDayString + "-" + orderMonthString + "-" + orderYearString, this)
                tanggal_event.error = null
            }, year, month, day)
            date.datePicker.maxDate = System.currentTimeMillis()
            if(orderYear != 0) date.updateDate(orderYear + 1, orderMonth - 1, orderDay)
            date.show()
        }

        add_btn_event.setOnClickListener {
            var date = tanggal_event.text.toString()
            var inputA = input1.text.toString()
            var inputB = input2.text.toString()
            var tipe = ""
            when(type){
                1 -> tipe = "Pertumbuhan"
                2 -> tipe = "Sakit"
                3 -> tipe = "Imunisasi"
                4 -> tipe = "Vitamin"
                5 -> tipe = "Mobilitas"
            }
            var cancel = false
            if (TextUtils.isEmpty(date)) {
                tanggal_event.error = "Bagian ini harus diisi"
                cancel = true
            }
            if (TextUtils.isEmpty(inputA)) {
                input1.error = "Bagian ini harus diisi"
                cancel = true
            }
            if (TextUtils.isEmpty(inputB)&&type == 1) {
                input2.error = "Bagian ini harus diisi"
                cancel = true
            }

            if(!cancel) {
                insertToDb(Analysis(babies[0].name,date,tipe,inputA,inputB))
                toast("Data Berhasil Di tambahkan")
                startActivity<MainActivity>()
                finish()
            }
        }
    }

    fun insertToDb(analysis: Analysis){
        compositeDisposable.add(Completable.fromRunnable { analysisDatabase?.analysisDao()?.insert(analysis) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },{
                    Log.d("anjing","gagal")
                }))
    }
}