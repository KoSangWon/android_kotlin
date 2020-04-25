package com.example.vocaquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import java.util.*

class MyAdapter(val items: ArrayList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view:View, data:String, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null
    var exampleArray = ArrayList<Int>()

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var voca:TextView = itemView.findViewById(R.id.voca)

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
        return 5
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val len = items.size

        while(exampleArray.size < 5){
            var num = Random().nextInt(len)
            if(!exampleArray.contains(num))
                exampleArray.add(num)
        }

        holder.voca.text = items[exampleArray[position]]
    }


}




//
//var flag = false
//
//while(!flag){
//    for(i in 0 until isVisited.size){
//        if(isVisited[i] == num) {
//            num = Random().nextInt(len)
//            continue
//        }
//    }
//    flag = true
//}
//isVisited.add(num)
