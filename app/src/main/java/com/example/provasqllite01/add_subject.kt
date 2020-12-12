package com.example.provasqllite01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class add_subject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)

        val DB = SQHelper(applicationContext)
        val title_input = findViewById<EditText>(R.id.textTitolo)
        val desc_input = findViewById<EditText>(R.id.textDesc)
        val add_btn = findViewById<Button>(R.id.addButt)

        add_btn.setOnClickListener{

            val title_text = title_input.text.toString().trim()
            val desc_text = desc_input.text.toString().trim()

            DB.ADD_DATA(title_text, desc_text)
            Toast.makeText(this, "Aggiunto", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))

        }


    }
}