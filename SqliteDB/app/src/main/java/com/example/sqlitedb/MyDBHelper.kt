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


}