package com.example.vocabulary

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
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
        var textView3: TextView = itemView.findViewById(R.id.textView3)

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
        holder.textView3.text = items[position]
        holder.textView3.visibility = GONE
    }


    fun moveItem(oldPos:Int, newPos:Int){
        val item = items.get(oldPos)
        items.removeAt(oldPos)
        items.add(newPos, item)
        notifyItemMoved(oldPos, newPos)
    }

    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }
}