package com.example.myfirebasedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class MyProductAdapter(options: FirebaseRecyclerOptions<Product>) :
    FirebaseRecyclerAdapter<Product, MyProductAdapter.ViewHolder>(options) {

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var productid:TextView
        var productname:TextView
        var productquantity:TextView
        init{
            productid = itemView.findViewById(R.id.productId)
            productname = itemView.findViewById(R.id.productname)
            productquantity = itemView.findViewById(R.id.productquantity)
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(it, adapterPosition)
            }
        }
    }

    interface OnItemClickListener{
        fun OnItemClick(view:View, position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Product) {
        holder.productid.text = model.pId.toString()
        holder.productname.text = model.pName
        holder.productquantity.text = model.pQuantity.toString()
    }

}