package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context?, var data: MutableList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null
        init {
            textView = itemView.findViewById(R.id.tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        var view = View.inflate(context, R.layout.item, null);
        var view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView?.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}