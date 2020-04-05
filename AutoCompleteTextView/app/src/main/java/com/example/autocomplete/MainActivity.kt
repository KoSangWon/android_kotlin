package com.example.autocomplete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val countries = mutableListOf(
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
            "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
            "Armenia", "Aruba", "Autralia", "Austria", "Azerbaijan", "Bahrain",
            "Bangladesh", "Barbades", "Belarus", "Belgium")

    lateinit var adapter:ArrayAdapter<String>;
    lateinit var countries2:Array<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        countries2 = resources.getStringArray(R.array.countries_array)
        adapter = ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                //countries//위에서 선언한 거 불러오기
                countries2//string.xml에서 불러오기
        )
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "선택된 항목 : $item", Toast.LENGTH_SHORT).show()
        }

        multiAutoCompleteTextView.setAdapter(adapter)
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }
}
