package com.example.vehiclecareapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vehiclecareapp.firebase.SignInActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val getStartedButton = findViewById<Button>(R.id.getStartedButton)
        getStartedButton.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}