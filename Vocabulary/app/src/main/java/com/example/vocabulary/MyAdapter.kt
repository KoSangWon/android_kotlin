package com.example.vocabulary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(val items: ArrayList<String>)
    :RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{

    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view:View, data:String, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var textView: TextView = itemView.findViewById(R.id.textView)
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
        holder.textView.text = items[position]
    }
}