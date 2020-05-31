package com.example.myfirebasedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyProductAdapter
    lateinit var rdb: DatabaseReference
    var findQuery = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initBtn()
    }

    fun initBtn(){
        insertbtn.setOnClickListener {
            val item = Product(
                Integer.parseInt(pIdEdit.text.toString()),
                pNameEdit.text.toString(),
                Integer.parseInt(pQuantityEdit.text.toString())

            )
            initAdapter()
            rdb.child(pIdEdit.text.toString()).setValue(item)
            pIdEdit.setText("")
            pNameEdit.setText("")
            pQuantityEdit.setText("")
        }

        deletebtn.setOnClickListener {
            initAdapter()
            rdb.child(pIdEdit.text.toString()).removeValue()
        }

        updatebtn.setOnClickListener {
            initAdapter()
            rdb.child(pIdEdit.text.toString())
                .child("pquantity")
                .setValue(Integer.parseInt(pQuantityEdit.text.toString()))
        }

        findbtn.setOnClickListener {
            if(findQuery)
                findQueryAdapter()
            else {
                findQuery = true
                findQueryAdapter()
            }
        }
    }

    fun initAdapter() {
        if(findQuery){
            findQuery=false
            if(adapter!=null)
                adapter.stopListening()
            val query = FirebaseDatabase.getInstance().reference.child("Products/items").limitToLast(50)
            val option = FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(query, Product::class.java)
                .build()
            adapter = MyProductAdapter(option)
            recyclerView.adapter = adapter
            adapter.startListening()
        }
    }

    fun findQueryAdapter(){
        if(adapter!=null)
            adapter.stopListening()
        val query = rdb.orderByChild("pname").equalTo(pNameEdit.text.toString())
        val option = FirebaseRecyclerOptions.Builder<Product>()
            .setQuery(query, Product::class.java)
            .build()
        adapter = MyProductAdapter(option)
        recyclerView.adapter = adapter
        adapter.startListening()
    }

    fun init(){
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        rdb = FirebaseDatabase.getInstance().getReference("Products/items")
        val query = FirebaseDatabase.getInstance().reference.child("Products/items").limitToLast(50)
        val option = FirebaseRecyclerOptions.Builder<Product>()
            .setQuery(query, Product::class.java)
            .build()
        adapter = MyProductAdapter(option)
        adapter.itemClickListener = object:MyProductAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                pIdEdit.setText(adapter.getItem(position).pId.toString())
                pNameEdit.setText(adapter.getItem(position).pName)
                pQuantityEdit.setText(adapter.getItem(position).pQuantity.toString())
            }
        }

        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
