package com.example.growby

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growby.adapter.ChatAdapter
import com.example.growby.adapter.OrderAdapter
import com.example.growby.data.analysis.Analysis
import com.example.growby.data.baby.Baby
import com.example.growby.data.baby.BabyDatabase
import com.example.growby.data.chat.Chat
import com.example.growby.data.chat.ChatDatabase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.chat_detail_activity.*
import kotlinx.android.synthetic.main.fragment_order.view.*
import org.jetbrains.anko.support.v4.onRefresh
import java.util.ArrayList

class ChatDetailActivity: AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()
    private val chats : ArrayList<Chat> = ArrayList()
    private var chatDatabase: ChatDatabase? = null
    lateinit var adapter :ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_detail_activity)
        chatDatabase = ChatDatabase.getInstance(this)
        chats.clear()
        chats.addAll(chatDatabase!!.chatDao().getAll())
        Log.d("pamungkas",chats.toString())
        swipe.onRefresh {
            swipe.isRefreshing =false
            chats.clear()
            chats.addAll(chatDatabase!!.chatDao().getAll())
        }
        init()
        onclick()

    }

    private fun onclick() {
       sent.setOnClickListener {
           var text = editText.text.toString()
           var cancel = false
           if(TextUtils.isEmpty(text)){
               editText.error = "Tidak bisa mengirim pesan kosong"
               cancel = true
           }
           if (!cancel){
               editText.hideKeyboard()
               editText.text = null
               insertToDb(Chat("me",text))
               chats.clear()
               chats.addAll(chatDatabase!!.chatDao().getAll())
               adapter.notifyDataSetChanged()
           }
       }
        back.setOnClickListener {
            finish()
        }
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun init() {
        rv_chat.layoutManager = LinearLayoutManager(this)
        adapter = ChatAdapter(chats,this)
        rv_chat.adapter = adapter
    }
    fun insertToDb(chat: Chat){
        compositeDisposable.add(Completable.fromRunnable { chatDatabase?.chatDao()?.insert(chat) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },{
                Log.d("anjing","gagal")
            }))
    }
}