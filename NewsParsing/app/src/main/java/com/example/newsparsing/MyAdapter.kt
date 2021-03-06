package com.example.newsparsing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import java.util.*

class MyAdapter(val items: ArrayList<MyData>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view:View, data:MyData, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var newtitle:TextView = itemView.findViewById(R.id.newtitle)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.newtitle.text = items[position].newstitle
    }
}