package com.example.growby

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.os.Looper
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.growby.data.baby.Baby
import com.example.growby.data.food.Food
import com.example.growby.data.order.Order
import com.example.growby.data.order.OrderDatabase
import com.google.android.gms.location.*
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_order.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.Exception
import java.util.*
import java.util.jar.Manifest


class OrderActivity: AppCompatActivity(){
    val REQUEST_CODE_LOCATION_PERMISSION = 1
    var longitude = 0.0
    var latitude = 0.0
    var isGet = false
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    val PERMISSION_ID = 1010
    lateinit var locationManager: LocationManager

    lateinit var model:OrderViewModel
    lateinit var modelFactory: OrderViewModelFactory

    val compositeDisposable = CompositeDisposable()
    private var orderDatabase: OrderDatabase? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        orderDatabase = OrderDatabase.getInstance(this)
        val data: Food? = intent.getParcelableExtra("food")
        Log.d("nendra",data.toString())
        food_name.text = data!!.name
        Glide.with(this).load(data.photo).into(food_photo)
        modelFactory = OrderViewModelFactory(data.price,1)
        model = ViewModelProvider(this,modelFactory).get(OrderViewModel::class.java)
        model.totalPrice.observe({lifecycle},{
            Log.d("nendra","price $it")
            total_price.text = "Rp.$it"
            total_price_total.text = "Rp.$it"
        })
        model.totalCount.observe({lifecycle},{
            count.text = it.toString()
            btn_minus.visibility = View.INVISIBLE
            if(it > 0){
                btn_minus.visibility = View.VISIBLE
                Log.d("nendra","count $it")
                btn_add.setOnClickListener { a ->
                    model.add()
                }
                btn_minus.setOnClickListener { b ->
                    model.sub()
                }
            }
        })
        back.setOnClickListener {
            finish()
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        lokasi_linear.setOnClickListener {
            Log.d("komdat:",checkPermission().toString())
            Log.d("komdat:",isLocationEnabled().toString())
            requestPermission()
            getLastLocation()
        }

        btn_order.setOnClickListener {
            var name = food_name.text.toString()
            var countFood = count.text.toString().toInt()
            var address = address_text.text.toString()
            var price = total_price_total.text.toString()
            var photo = data.photo
            insertToDb(Order(name,countFood,photo,address,price,false))
            toast("Data Berhasil Di tambahkan")
            startActivity<MainActivity>()
            finish()
        }
    }

    fun insertToDb(order: Order){
        compositeDisposable.add(Completable.fromRunnable { orderDatabase?.orderDao()?.insert(order) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },{
                Log.d("anjing","gagal")
            }))
    }


    fun getLastLocation(){
        if(checkPermission()){
            if(isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {task->
                    var location:Location? = task.result
                    if(location == null){
                        newLocationData()
                    }else{
                        Log.d("komdat:" ,"You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude + "\n" + getCityName(location.latitude,location.longitude))
                        address_text.text = getCityName(location.latitude,location.longitude)
                        //textView.text = "You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude + "\n" + getCityName(location.latitude,location.longitude)
                    }
                }
            }else{
                Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
            }
        }else{
            requestPermission()
        }
    }

    fun newLocationData(){
        var locationRequest =  LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()
        )
    }


    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("komdat:","You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude))
            address_text.text = getCityName(lastLocation.latitude,lastLocation.longitude)
           // textView.text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude)
        }
    }

    private fun getCityName(lat: Double,long: Double): String {
        var cityName:String = ""
        var countryName = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat,long,3)

        cityName = Adress.get(0).locality
        countryName = Adress.get(0).countryName
        Log.d("komdat:",Adress[0].getAddressLine(0))
        return  Adress[0].getAddressLine(0) + cityName
    }

    fun checkPermission():Boolean{
        //this function will return a boolean
        //true: if we have permission
        //false if not
        if(
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }

        return false

    }

    fun requestPermission(){
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    fun isLocationEnabled():Boolean{
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

/*
    private fun currentLocation() {
        showProgress(true)

        val location = LocationRequest()
        location.interval = 10000
        location.fastestInterval = 3000
        location.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
            Log.d("nendra","${it} ")
            latitude = it.latitude
            longitude = it.longitude
            isGet = true
            showProgress(false)

        }
    }
*/


    private fun showProgress(show: Boolean) {
        order_progress.bringToFront()
        order_progress.visibility = if (show) View.VISIBLE else View.GONE
        disableTouch(show)
    }

    private fun disableTouch(status: Boolean){
        if(status){
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

}