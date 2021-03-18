package com.example.growby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.growby.R
import com.example.growby.data.chat.Chat
import kotlinx.android.synthetic.main.item_chat.view.*


class ChatAdapter(private val chats: List<Chat>, private val context: Context): RecyclerView.Adapter<ChatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat,parent,false))

        return view
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position],context)
    }

    override fun getItemCount(): Int = chats.size
}

class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name = view.text_chat


    fun bind(chat: Chat, context: Context){
        name.text = chat.text
    }

}
