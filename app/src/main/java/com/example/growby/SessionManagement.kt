package com.example.growby

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import org.json.JSONException

class SessionManagement  ( var context: Context) {
    companion object{
        private val PREF_NAME = "AndroidHivePref"

        // All Shared Preferences Keys
        private val IS_LOGIN = "IsLoggedIn"
        private val IS_FIRST = "isFirstOpen"
        private val IS_HOME = "isHomeOpen"
        private val IS_EMPTY = "isBabyEmpty"






        // User name (make variable public to access from outside)

        // Email address (make variable public to access from outside)
        val KEY_EMAIL = "email"
        val KEY_NAME = "name"
        val KEY_DATE = "date"
        val KEY_PASS = "password"



    }


    var pref: SharedPreferences

    var editor: SharedPreferences.Editor

    var PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }


    val password: String
        get() {
            return pref.getString(KEY_PASS,"").toString()
        }

    val user: HashMap<String,String>
        get() {
            val  user = HashMap<String , String>()
            user[KEY_EMAIL] = pref.getString(KEY_EMAIL, "").toString()
            user[KEY_NAME] = pref.getString(KEY_NAME, "").toString()
            user[KEY_PASS] = pref.getString(KEY_PASS,"").toString()
            user[KEY_DATE] = pref.getString(KEY_DATE,"").toString()

            return user
        }



    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    val isFirstOpen: Boolean
        get() = pref.getBoolean(IS_FIRST, false)

    val isEmptyBaby: Boolean
        get() = pref.getBoolean(IS_EMPTY,true)

    val isHomeOpen: Boolean
        get() = pref.getBoolean(IS_HOME, false)

    fun addBaby(){
        editor.putBoolean(IS_EMPTY,false)
        editor.commit()
    }

    @Throws(JSONException::class)
    fun createOnBoardSession() {
        editor.putBoolean(IS_FIRST,true)
        editor.commit()
    }

    fun createUser(email: String,password: String,name: String,date:String){

        editor.putString(KEY_EMAIL,email)
        editor.putString(KEY_PASS,password)
        editor.putString(KEY_NAME,name)
        editor.putString(KEY_DATE,date)
        editor.commit()
    }


    fun createLoginSession(){
        editor.putBoolean(IS_LOGIN,true)
        editor.commit()
    }



    fun checkLogin(): Boolean = this.isLoggedIn
    fun checkFirst(): Boolean = this.isFirstOpen
    fun checkEmpty(): Boolean = this.isEmptyBaby


    fun logoutUser() {
        editor.remove(IS_LOGIN)
        editor.remove(KEY_EMAIL)
        editor.remove(KEY_NAME)
        editor.remove(KEY_PASS)
        editor.commit()

        val intent = Intent(context,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}