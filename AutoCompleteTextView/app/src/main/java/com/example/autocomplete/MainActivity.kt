package com.example.autocomplete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    lateinit var adapter:ArrayAdapter<String>
    //lateinit var countries2:Array<String>//string.xml에 선언한 것 사용할 때 이렇게 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        //countries2 = resources.getStringArray(R.array.countries_array)//string.xml에서 선언한 거 사용할 때 써줘야함
        adapter = ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                countries//위에서 선언한 거 불러오기
                //countries2//string.xml에서 불러오기
        )
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "선택된 항목 : $item", Toast.LENGTH_SHORT).show()
        }

        multiAutoCompleteTextView.setAdapter(adapter)
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        editText.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                inputBtn.isEnabled = str.isNotEmpty()//str에 값이 존재할 때 true로 바꿔줌. 버튼 활성화시킴.
                //TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }
        })

        //버튼을 만들어줬으니 Listener를 달아줘야한다.
        inputBtn.setOnClickListener {
            adapter.add(editText.text.toString())//countries배열(mutable)에 추가
            adapter.notifyDataSetChanged()//list에 해당하는 내용들이 갱신된다.
            editText.text.clear()//editText에 적혀있는 내용은 지우고 싶다.
        }
    }
}
