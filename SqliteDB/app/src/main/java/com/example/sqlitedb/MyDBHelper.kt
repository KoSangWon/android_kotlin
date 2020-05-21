package com.example.sqlitedb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MyDBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_VERSION = 1
        val DB_NAME = "mydb.db"
        val TABLE_NAME = "products"
        val PID = "pid"
        val PNAME = "pname"
        val PQUANTITY = "pquantity"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table = "create table if not exists " + TABLE_NAME + "(" +
                PID + " integer primary key autoincrement, " +
                PNAME + " text, " +
                PQUANTITY + " integer)"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists " + TABLE_NAME
        db?.execSQL(drop_table)
        onCreate(db)
    }

    fun insertProduct(product: Product):Boolean{
        val values = ContentValues()
        values.put(PNAME, product.pName)
        values.put(PQUANTITY, product.pQuantity)
        val db = this.writableDatabase
        if(db.insert(TABLE_NAME, null, values) > 0){
            db.close()
            return true
        }else{
            db.close()
            return false
        }
    }
    fun getAllRecord(){
        val strsql = "select * from " + TABLE_NAME
        val db = this.readableDatabase
        val cursor = db.rawQuery(strsql, null)
        if(cursor.count != 0){
            showRecord(cursor)
        }
        cursor.close()
        db.close()
    }

    fun showRecord(cursor: Cursor){
        cursor.moveToFirst()
        val count = cursor.columnCount
        val recordcount = cursor.count
        val activity = context as MainActivity
        activity.tableLayout.removeAllViewsInLayout()
        // 컬럼 타이틀 만들기
        val tablerow = TableRow(activity)
        val rowParam = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, count.toFloat())
        tablerow.layoutParams = rowParam
        val viewParam = TableRow.LayoutParams(0, 100, 1f)
        for(i in 0 until count){
            val textView = TextView(activity)
            textView.layoutParams = viewParam
            textView.text = cursor.getColumnName(i)
            textView.setBackgroundColor(Color.LTGRAY)
            textView.textSize = 15.0f
            textView.gravity = Gravity.CENTER
        }
        activity.tableLayout.addView(tablerow)
        // 실제 레코드 읽어오기
        do{
            val row = TableRow(activity)
            row.layoutParams = rowParam
            for(i in 0 until count){
                val textView = TextView(activity)
                textView.layoutParams = viewParam
                textView.text = cursor.getString(i)
                textView.textSize = 13.0f
                row.addView(textView)
            }
            activity.tableLayout.addView(row)
        }while (cursor.moveToNext())
    }
}