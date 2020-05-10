package com.example.todolist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class ActivityList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val inputField = findViewById<TextInputEditText>(R.id.input_field2)
        val sPref: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val sEdit: SharedPreferences.Editor = sPref.edit()
//
        val str:String? = sPref.getString("text", "")

        inputField.setText(str)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val inputField = findViewById<TextInputEditText>(R.id.input_field2)
        val sPref: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val sEdit: SharedPreferences.Editor = sPref.edit()


        sEdit.putString("text", inputField.text.toString())
        sEdit.commit()
        Toast.makeText(this, inputField.text.toString(), Toast.LENGTH_SHORT).show()
    }
}
