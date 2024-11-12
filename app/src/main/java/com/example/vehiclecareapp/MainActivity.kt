package com.example.vehiclecareapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var admin:Button
    lateinit var customer:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        admin= findViewById(R.id.admin)
        customer= findViewById(R.id.Customer)

        admin.setOnClickListener {

            val intent= Intent(this, AdminEnterDetail::class.java)
            startActivity(intent)
        }

        customer.setOnClickListener {

            val intent= Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }



    }
}